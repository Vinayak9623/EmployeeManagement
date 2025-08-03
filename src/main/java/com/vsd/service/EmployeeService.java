package com.vsd.service;

import com.vsd.Dto.request.EmployeeRequest;
import com.vsd.Dto.response.EmployeeResponse;
import com.vsd.common.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeService {

ApiResponse<EmployeeResponse> createEmployee(EmployeeRequest employeeDto);

ApiResponse<List<EmployeeResponse>> getAllEmployee();

}
