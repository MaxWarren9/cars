package com.example.cars.controllers;

import com.example.cars.model.dto.request.CarInfoRequest;
import com.example.cars.model.dto.request.CarToUserRequest;
import com.example.cars.model.dto.response.CarInfoResponse;
import com.example.cars.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public Page<CarInfoResponse> getAllCars(@RequestParam(defaultValue = "1") Integer page,
                                            @RequestParam(defaultValue = "10") Integer perPage,
                                            @RequestParam(defaultValue = "modelName") String sort,
                                            @RequestParam(defaultValue = "ASC") Sort.Direction order,
                                            @RequestParam(required = false) String filter
    ) {
        return carService.getAllCars(page, perPage,sort,order, filter);
    }

    @PostMapping("/carToUser")
    @Operation(summary = "Добавить автомобиль пользователю")
    public void addCarToUser(@RequestBody @Valid CarToUserRequest request) {
        carService.addCarToUser(request);
    }
    @PostMapping("/userCars/{id}")
    @Operation(summary = "Получить список автомобилей пользователя")
    public Page<CarInfoResponse> getUserCars(@PathVariable Long id,
                                             @RequestParam(defaultValue = "1") Integer page,
                                             @RequestParam(defaultValue = "10") Integer perPage,
                                             @RequestParam(defaultValue = "modelName") String sort,
                                             @RequestParam(defaultValue = "ASC") Sort.Direction order,
                                             @RequestParam(required = false) String filter
    ) {
        return carService.getUserCars(id, page, perPage, sort, order, filter);
    }
}
