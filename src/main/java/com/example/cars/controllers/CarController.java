package com.example.cars.controllers;

import com.example.cars.model.dto.request.CarInfoRequest;
import com.example.cars.model.dto.response.CarInfoResponse;
import com.example.cars.service.CarService;
import com.example.cars.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.example.cars.constants.Constants.CAR_API;
@Tag(name = "Cars")
@RestController
@RequestMapping(CAR_API)
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @PostMapping
    @Operation(summary = "Создать авто")
        public CarInfoResponse createCar(@RequestBody @Valid CarInfoRequest request) {
            return carService.createCar(request);
        }

        //получение
        @GetMapping("/{id}")
        @Operation(summary = "Получить авто по id")
        public CarInfoResponse getCar(@PathVariable Long id) {
            return carService.getCar(id);
        }

        //обновить информацию
        @PutMapping("/{id}")
        @Operation(summary = "Обновить авто по id")
        public CarInfoResponse updateCar(@PathVariable Long id, @RequestBody CarInfoRequest request) {
            return carService.updateCar(id, request);
        }

        //удаление
        @DeleteMapping("/{id}")
        @Operation(summary = "Удалить авто по id")
        public void deleteCar(@PathVariable Long id) {
            carService.deleteCar(id);
        }

        //получение всех cars
        @GetMapping("/all")
        @Operation(summary = "Получить все авто")
        public List<CarInfoResponse> getAllCars() {
            return carService.getAllCars();
        }
    }
