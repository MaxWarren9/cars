package com.example.cars.controllers;

import com.example.cars.model.dto.request.UserInfoRequest;
import com.example.cars.model.dto.response.UserInfoResponse;
import com.example.cars.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.cars.constants.Constants.USER_API;
@Tag(name = "Пользователь")
@RestController
@RequestMapping(USER_API)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    @Operation(summary = " Попытки создать пользователя")
    public UserInfoResponse createUser(@RequestBody UserInfoRequest request) {
        return userService.createUser(request);
    }

    @PostMapping("/{id}")
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
    public List<UserInfoResponse> getAllUsers() {
        return userService.getAllUsers();
    }
}
