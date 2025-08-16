package com.vsd.controller;

import com.vsd.Dto.request.EmployeeRequest;
import com.vsd.Dto.response.EmployeeResponse;
import com.vsd.common.ApiResponse;
import com.vsd.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @PostMapping("/createAll")
    public ResponseEntity<ApiResponse<List<EmployeeResponse>>> createListOfEmployee(@RequestBody List<EmployeeRequest> requests){
        ApiResponse<List<EmployeeResponse>> listOfEmployee = employeeService.createListOfEmployee(requests);
        return ResponseEntity.status(listOfEmployee.getStatus()).body(listOfEmployee);
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

    @GetMapping("/page")
    public ResponseEntity<ApiResponse<Page<EmployeeResponse>>> getPagenatedEmployees(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "5") int size){
        ApiResponse<Page<EmployeeResponse>> pagableEmployee = employeeService.getPagableEmployee(page, size);
        return ResponseEntity.status(pagableEmployee.getStatus()).body(pagableEmployee);

    }
}
