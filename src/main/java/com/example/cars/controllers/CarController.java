package com.example.cars.controllers;

import com.example.cars.model.dto.request.CarInfoRequest;
import com.example.cars.model.dto.response.CarInfoResponse;
import com.example.cars.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.example.cars.constants.Constants.CARS;
@Tag(name = "Автомобили")
@RestController
@RequestMapping(CARS)
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
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Успех"),
                @ApiResponse(responseCode = "404", description = "Не найден"),
                @ApiResponse(responseCode = "401", description = "Не авторизован"),
        })
        @Operation(summary = "Получить список автомобилей")
        public List<CarInfoResponse> getAllCars() {
            return carService.getAllCars();
        }
    }
