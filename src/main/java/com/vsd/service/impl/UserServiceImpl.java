package com.vsd.service.impl;

import com.vsd.Dto.request.UserRequest;
import com.vsd.Dto.response.UserResponse;
import com.vsd.Repository.UserRepository;
import com.vsd.common.ApiResponse;
import com.vsd.entity.Role;
import com.vsd.entity.User;
import com.vsd.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ApiResponse<UserResponse> registerUser(UserRequest userRequest) {

        if(userRepository.findByEmail(userRequest.getEmail()).isPresent()){
            return new ApiResponse<>(HttpStatus.CONFLICT.value(), "Email Already exist",null,null);
        }

        User user;
        if(userRequest.getId()==null){
            user=User.builder().id(userRequest.getId())
                    .username(userRequest.getUsername())
                    .email(userRequest.getEmail())
                    .password(passwordEncoder.encode(userRequest.getPassword()))
                    .roles(userRequest.getRoles()).build();
        }
        else {
            user=User.builder()
                    .username(userRequest.getUsername())
                    .email(userRequest.getEmail())
                    .password(passwordEncoder.encode(userRequest.getPassword()))
                    .roles(userRequest.getRoles()).build();
        }
        User save = userRepository.save(user);
        UserResponse response =UserResponse.builder().id(save.getId())
                    .username(save.getUsername())
                    .email(save.getEmail())
                    .password(save.getPassword())
                    .roles(userRequest.getRoles()).build();
        String message=userRequest.getId()==null? "User created successfully" : "User Updated successfully";
        return new ApiResponse<>(HttpStatus.CREATED.value(), message,response,null);
    }
}
