package com.example.cars.service;

import com.example.cars.model.CarStatus;
import com.example.cars.model.UserStatus;
import com.example.cars.model.db.entity.Car;
import com.example.cars.model.db.entity.User;
import com.example.cars.model.db.repository.CarRepository;
import com.example.cars.model.dto.request.CarInfoRequest;
import com.example.cars.model.dto.response.CarInfoResponse;
import com.example.cars.model.dto.response.UserInfoResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CarService {
    private final ObjectMapper mapper;
    private final CarRepository carRepository;


    public CarInfoResponse createCar(CarInfoRequest request) {
        if (!EmailValidator.getInstance().isValid(request.getEmail())) {
            return null;
        }

        Car car = mapper.convertValue(request, Car.class);
        car.setCreatedAt(LocalDateTime.now());
        car.setStatus(CarStatus.CREATED);

        Car save = carRepository.save(car);

        return mapper.convertValue(save, CarInfoResponse.class);
    }

    public CarInfoResponse getCar(Long id) {

    Car car = getCarFromDB(id);
        return mapper.convertValue(car, CarInfoResponse.class);
}
private Car getCarFromDB(Long id) {
    return carRepository.findById(id).orElse(new Car());
}

    public CarInfoResponse updateCar(Long id, CarInfoRequest request) {
        if (!EmailValidator.getInstance().isValid(request.getEmail())) {
            return null;
        }

        Car car = getCarFromDB(id);
        car.setModelName(request.getModelName()==null ? car.getModelName(): request.getModelName());
        car.setColor(request.getColor()==null ? car.getColor(): request.getColor());
        car.setEngineCapacity(request.getEngineCapacity()==null ? car.getEngineCapacity(): request.getEngineCapacity());
        car.setIsNew(request.getIsNew()==null ? car.getIsNew(): request.getIsNew());
        car.setPrice(request.getPrice()==null ? car.getPrice(): request.getPrice());
        car.setYear(request.getYear()==null ? car.getYear(): request.getYear());

        car.setUpdatedAt(LocalDateTime.now());
        car.setStatus(CarStatus.UPDATED);
        Car save = carRepository.save(car);
        return mapper.convertValue(save, CarInfoResponse.class);
    }

    public void deleteCar(Long id) {
        Car car = getCarFromDB(id);
        car.setUpdatedAt(LocalDateTime.now());
        car.setStatus(CarStatus.DELETED);
        carRepository.save(car);
    }

    public List<CarInfoResponse> getAllCars() {
        return carRepository.findAll().stream()
                .map(car -> mapper.convertValue(car, CarInfoResponse.class))
                .collect(Collectors.toList());
    }
}
