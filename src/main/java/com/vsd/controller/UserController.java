package com.vsd.controller;

import com.vsd.Dto.request.UserRequest;
import com.vsd.Dto.response.UserResponse;
import com.vsd.common.ApiResponse;
import com.vsd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponse>> createOrUpdateUser(@RequestBody UserRequest userRequest){
        ApiResponse<UserResponse> response = userService.registerUser(userRequest);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
