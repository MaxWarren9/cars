package com.example.cars.service;

import com.example.cars.exceptions.CustomException;
import com.example.cars.model.CarStatus;
import com.example.cars.model.db.entity.Car;
import com.example.cars.model.db.entity.User;
import com.example.cars.model.db.repository.CarRepository;
import com.example.cars.model.dto.request.CarInfoRequest;
import com.example.cars.model.dto.request.CarToUserRequest;
import com.example.cars.model.dto.response.CarInfoResponse;
import com.example.cars.utils.PaginationUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CarService {
    private final UserService userService;
    private final ObjectMapper mapper;
    private final CarRepository carRepository;


    public CarInfoResponse createCar(CarInfoRequest request) {

        Car car = mapper.convertValue(request, Car.class);
        car.setCreatedAt(LocalDateTime.now());
        car.setStatus(CarStatus.CREATED);

        Car save = carRepository.save(car);

        return mapper.convertValue(save, CarInfoResponse.class);
    }

    public CarInfoResponse getCar(Long id) {

        Car car = getCarById(id);
        return mapper.convertValue(car, CarInfoResponse.class);
    }

    private Car getCarById(Long id) {
        return carRepository.findById(id).orElseThrow(() -> new CustomException("car not found", HttpStatus.NOT_FOUND));
    }

    public CarInfoResponse updateCar(Long id, CarInfoRequest request) {

        Car car = getCarById(id);
        car.setModelName(request.getModelName() == null ? car.getModelName() : request.getModelName());
        car.setColor(request.getColor() == null ? car.getColor() : request.getColor());
        car.setEngineCapacity(request.getEngineCapacity() == null ? car.getEngineCapacity() : request.getEngineCapacity());
        car.setIsNew(request.getIsNew() == null ? car.getIsNew() : request.getIsNew());
        car.setPrice(request.getPrice() == null ? car.getPrice() : request.getPrice());
        car.setYear(request.getYear() == null ? car.getYear() : request.getYear());

        car.setUpdatedAt(LocalDateTime.now());
        car.setStatus(CarStatus.UPDATED);
        Car save = carRepository.save(car);
        return mapper.convertValue(save, CarInfoResponse.class);
    }

    public void deleteCar(Long id) {
        Car car = getCarById(id);
        car.setUpdatedAt(LocalDateTime.now());
        car.setStatus(CarStatus.DELETED);
        carRepository.save(car);
    }

    public Page<CarInfoResponse> getAllCars(Integer page, Integer perPage, String sort, Sort.Direction order, String filter) {
        Pageable pageRequest = PaginationUtils.getPageRequest(page, perPage, sort, order);
        Page<Car> all;
        if (filter == null) {
            all = carRepository.findAllByStatusNot(pageRequest, CarStatus.DELETED);
        } else {
            all = carRepository.findAllByStatusNotFiltered(pageRequest, CarStatus.DELETED, filter.toLowerCase());
        }

        List<CarInfoResponse> content = all.getContent().stream()
                .map(car -> mapper.convertValue(car, CarInfoResponse.class))
                .collect(Collectors.toList());
        return new PageImpl<>(content, pageRequest, all.getTotalElements());
    }

    public void addCarToUser(CarToUserRequest request) {
        Car car = getCarById(request.getCarId());
        User userFromDB = userService.getUserFromDB(request.getUserId());
        userFromDB.getCars().add(car);
        userService.updateUserData(userFromDB);
        car.setUser(userFromDB);
        carRepository.save(car);
    }

    public Car getSomeCar() {
        return carRepository.getSomeCar(true);
    }

    public Page<CarInfoResponse> getUserCars(Long id, Integer page, Integer perPage, String sort, Sort.Direction order, String filter) {
        Pageable pageRequest = PaginationUtils.getPageRequest(page, perPage, sort, order);
        Page<Car> userCars;
        if (filter == null) {
            userCars = carRepository.findAllCarsByStatusNot(id, pageRequest, CarStatus.DELETED);
        } else {
            userCars = carRepository.findAllCarsByStatusNotFiltered(id, pageRequest, CarStatus.DELETED, filter.toLowerCase());
        }

        List<CarInfoResponse> content = userCars.getContent().stream()
                .map(car -> mapper.convertValue(car, CarInfoResponse.class))
                .collect(Collectors.toList());

        return new PageImpl<>(content, pageRequest, userCars.getTotalElements());
    }

}
