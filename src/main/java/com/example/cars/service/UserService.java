package com.example.cars.service;

import com.example.cars.exceptions.CustomException;
import com.example.cars.model.UserStatus;
import com.example.cars.model.db.entity.User;
import com.example.cars.model.db.repository.UserRepository;
import com.example.cars.model.dto.request.UserInfoRequest;
import com.example.cars.model.dto.response.UserInfoResponse;
import com.example.cars.utils.PaginationUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.EmailValidator;
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
public class UserService {
    private final ObjectMapper mapper;
    private final UserRepository userRepository;

    public UserInfoResponse createUser(UserInfoRequest request) {
        validateEmail(request);
        userRepository.findByEmailIgnoreCase(request.getEmail())
                .ifPresent(user -> {
                    throw new CustomException(String.format("user with email: %s already exists", request.getEmail()), HttpStatus.BAD_REQUEST);
                });

        User user = mapper.convertValue(request, User.class);
        user.setCreatedAt(LocalDateTime.now());
        user.setStatus(UserStatus.CREATED);

        User save = userRepository.save(user);

        return mapper.convertValue(save, UserInfoResponse.class);
    }

    private void validateEmail(UserInfoRequest request) {
        if (!EmailValidator.getInstance().isValid(request.getEmail())) {
            throw new CustomException("Invalid email format", HttpStatus.BAD_REQUEST);
        }
    }
    public UserInfoResponse getUser(Long id) {
//        Optional<User> optionalUser = userRepository.findById(id);
//        User user;
//        if(optionalUser.isPresent()) {
//            user = optionalUser.get();
//            { else{
//                user = null;
//            }
//            }
//        }
//        User user = userRepository.findById(id).orElse(new User());
        User user = getUserFromDB(id);
        return mapper.convertValue(user, UserInfoResponse.class);
    }

    public User getUserFromDB(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new CustomException("User not found", HttpStatus.NOT_FOUND));
    }

    public UserInfoResponse updateUser(Long id, UserInfoRequest request) {
        validateEmail(request);
        userRepository.findByEmailIgnoreCase(request.getEmail());
        User user = getUserFromDB(id);

        user.setEmail(request.getEmail());
        user.setGender(request.getGender() == null ? user.getGender() : request.getGender());
        user.setAge(request.getAge() == null ? user.getAge() : request.getAge());
        user.setFirstName(request.getFirstName() == null ? user.getFirstName() : request.getFirstName());
        user.setLastName(request.getLastName() == null ? user.getLastName() : request.getLastName());
        user.setMiddleName(request.getMiddleName() == null ? user.getMiddleName() : request.getMiddleName());
        user.setPassword(request.getPassword() == null ? user.getPassword() : request.getPassword());

        user.setUpdatedAt(LocalDateTime.now());
        user.setStatus(UserStatus.UPDATED);
        User save = userRepository.save(user);
        return mapper.convertValue(save, UserInfoResponse.class);

    }

    public void deleteUser(Long id) {
//        userRepository.deleteById(id);
        User user = getUserFromDB(id);
        user.setUpdatedAt(LocalDateTime.now());
        user.setStatus(UserStatus.DELETED);
        userRepository.save(user);
    }

/*
    public Page<UserInfoResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> mapper.convertValue(user, UserInfoResponse.class))
                .collect(Collectors.toList());
    }
    */
    public User updateUserData(User user) {
        return userRepository.save(user);
    }


    public Page<UserInfoResponse> getAllUsers(Integer page, Integer perPage, String sort, Sort.Direction order, String filter) {

        Pageable pageRequest = PaginationUtils.getPageRequest(page, perPage, sort, order);
        Page<User> all;
        if (filter == null) {
            all = userRepository.findAllByStatusNot(pageRequest, UserStatus.DELETED);
        } else {
            all = userRepository.findAllByStatusNotFiltered(pageRequest, UserStatus.DELETED, filter.toLowerCase());
        }

        List<UserInfoResponse> content = all.getContent().stream()
                .map(user -> mapper.convertValue(user, UserInfoResponse.class))
                .collect(Collectors.toList());

        return new PageImpl<>(content, pageRequest, all.getTotalElements());
    }
}
