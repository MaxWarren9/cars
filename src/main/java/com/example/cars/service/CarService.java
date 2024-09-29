package com.example.cars.service;

import com.example.cars.model.dto.request.CarInfoRequest;
import com.example.cars.model.dto.response.CarInfoResponse;
import com.example.cars.model.dto.response.UserInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CarService {

    public CarInfoResponse createCar(CarInfoRequest request) {
        if (!EmailValidator.getInstance().isValid(request.getEmail())) {
            return null;
        }
        return CarInfoResponse.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .modelName(request.getModelName())
                .modelYear(request.getModelYear())
                .color(request.getColor())
                .price(request.getPrice())
                .engineCapacity(request.getEngineCapacity())
                .build();
    }

    public CarInfoResponse getCar(Long id) {
        return null;
    }

    public CarInfoResponse updateCar(Long id, CarInfoRequest request) {
        if (!EmailValidator.getInstance().isValid(request.getEmail())) {
            return null;
        }
        return CarInfoResponse.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .modelName(request.getModelName())
                .modelYear(request.getModelYear())
                .color(request.getColor())
                .price(request.getPrice())
                .engineCapacity(request.getEngineCapacity())
                .build();
    }

    public void deleteCar(Long id) {
    }

    public List<CarInfoResponse> getAllCars() {
        return Collections.emptyList();
    }
}
