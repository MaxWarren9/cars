package com.example.cars.controllers;

import com.example.cars.model.dto.request.UserInfoRequest;
import com.example.cars.model.dto.response.UserInfoResponse;
import com.example.cars.service.CarService;
import com.example.cars.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import static com.example.cars.constants.Constants.USERS;
@Tag(name = "Пользователь")
@RestController
@RequestMapping(USERS)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final CarService carService;

    @PostMapping
    @Operation(summary = " Попытки создать пользователя")
    public UserInfoResponse createUser(@RequestBody UserInfoRequest request) {
        return userService.createUser(request);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить пользователя по id")
    public UserInfoResponse getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить пользователя по id")
    public UserInfoResponse updateUser(@PathVariable Long id, @RequestBody UserInfoRequest request) {
        return userService.updateUser(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить пользователя по id")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/all")
    @Operation(summary = "Получить список пользователей")
//    public Page<UserInfoResponse> getAllUsers(@RequestParam(defaultValue = "1") Integer page,
//                                              @RequestParam(defaultValue = "10") Integer perPage,
//                                              @RequestParam(defaultValue = "lastName") String sort,
//                                              @RequestParam(defaultValue = "ASC") Sort.Direction order,
//                                              @RequestParam(required = false) String filter
//     ) {
//        return userService.getAllUsers(page, perPage,sort,order,filter);
    public Page<UserInfoResponse> getAllUsers(@RequestParam(defaultValue = "1") Integer page,
                                              @RequestParam(defaultValue = "10") Integer perPage,
                                              @RequestParam(defaultValue = "lastName") String sort,
                                              @RequestParam(defaultValue = "ASC") Sort.Direction order,
                                              @RequestParam(required = false) String filter
    ) {
        return userService.getAllUsers(page, perPage, sort, order, filter);
    }
}
