package com.vsd.controller;

import com.vsd.Dto.request.EmployeeRequest;
import com.vsd.Dto.response.EmployeeResponse;
import com.vsd.common.ApiResponse;
import com.vsd.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<EmployeeResponse>> createEmployee(@RequestBody EmployeeRequest employee){
        ApiResponse<EmployeeResponse> response = employeeService.createEmployee(employee);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/employee")
    public ResponseEntity<ApiResponse<List<EmployeeResponse>>> getEmployees(){
        ApiResponse<List<EmployeeResponse>> allEmployee = employeeService.getAllEmployee();
        return ResponseEntity.status(allEmployee.getStatus()).body(allEmployee);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<?>> deleteEmployee(@PathVariable long id){
        ApiResponse<?> apiResponse = employeeService.deleteEmployee(id);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);

    }
}
