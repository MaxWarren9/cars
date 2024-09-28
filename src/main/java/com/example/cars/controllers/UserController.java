package com.example.cars.controllers;

import com.example.cars.model.dto.request.UserInfoRequest;
import com.example.cars.model.dto.response.UserInfoResponse;
import com.example.cars.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

import static com.example.cars.constants.Constants.USER_API;

@RestController
@RequestMapping(USER_API)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping
    public UserInfoResponse createUser(@RequestBody UserInfoRequest request) {
        return new UserInfoResponse();
    }

    @PostMapping("/{id}")
    public UserInfoResponse getUser(@PathVariable Long id) {
        return new UserInfoResponse();
    }

    @PutMapping("/{id}")
    public UserInfoResponse updateUser(@PathVariable Long id, @RequestBody UserInfoRequest request) {
        return new UserInfoResponse();
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
    }

    @GetMapping("/all")
    public List<UserInfoResponse> getAllUsers() {
        return Collections.singletonList(new UserInfoResponse());
    }
}
