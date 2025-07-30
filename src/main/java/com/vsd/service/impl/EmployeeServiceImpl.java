package com.vsd.service.impl;

import com.vsd.Dto.request.EmployeeRequest;
import com.vsd.Dto.response.EmployeeResponse;
import com.vsd.Repository.EmployeeRepo;
import com.vsd.common.ApiResponse;
import com.vsd.entity.Employee;
import com.vsd.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ApiResponse<EmployeeResponse> createEmployee(EmployeeRequest employeeDto) {

        if(employeeRepo.existsByEmail(employeeDto.getEmail())){
            return new ApiResponse<>(HttpStatus.CONFLICT.value(),"Email already exist",null,null);
        }

        Employee employee = Employee.builder().name(employeeDto.getName())
                .email(employeeDto.getEmail())
                .department(employeeDto.getDepartment())
                .position(employeeDto.getPosition())
                .salary(employeeDto.getSalary())
                .dateOfJoining(employeeDto.getDateOfJoining()).build();

        employee = employeeRepo.save(employee);

        EmployeeResponse employeeResponse = EmployeeResponse.builder()
                .id(employee.getId())
                .name(employee.getName())
                .email(employee.getEmail())
                .department(employee.getDepartment())
                .position(employee.getPosition())
                .salary(employee.getSalary())
                .dateOfJoining(employee.getDateOfJoining()).build();

        return new ApiResponse<>(HttpStatus.CREATED.value(),"Employee created successfully",employeeResponse,null);

    }
}
