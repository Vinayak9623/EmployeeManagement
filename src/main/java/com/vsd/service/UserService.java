package com.vsd.service;

import com.vsd.Dto.request.UserRequest;
import com.vsd.Dto.response.UserResponse;
import com.vsd.common.ApiResponse;

public interface UserService {

    public ApiResponse<UserResponse> registerUser(UserRequest userRequest);
}
