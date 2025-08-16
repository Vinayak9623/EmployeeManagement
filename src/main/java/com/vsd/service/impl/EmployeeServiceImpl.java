package com.vsd.service.impl;

import com.vsd.Dto.Projection.EmployeeBesicProjection;
import com.vsd.Dto.request.EmployeeRequest;
import com.vsd.Dto.response.EmployeeResponse;
import com.vsd.Repository.EmployeeRepo;
import com.vsd.common.ApiResponse;
import com.vsd.entity.Employee;
import com.vsd.exception.customeEx.EmployeeNotFoundException;
import com.vsd.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ApiResponse<EmployeeResponse> createEmployee(EmployeeRequest employeeDto) {

        Employee employee;
        if(employeeDto.getId()==null) {
            if (employeeRepo.existsByEmail(employeeDto.getEmail())) {
                return new ApiResponse<>(HttpStatus.CONFLICT.value(), "Email already exist", null, null);
            }

      employee = Employee.builder().name(employeeDto.getName())
                    .email(employeeDto.getEmail())
                    .department(employeeDto.getDepartment())
                    .position(employeeDto.getPosition())
                    .salary(employeeDto.getSalary())
                    .dateOfJoining(employeeDto.getDateOfJoining()).build();}
        else {
        employee = employeeRepo.findById(employeeDto
                .getId()).orElseThrow(
                        () -> new  EmployeeNotFoundException("Employee not found with ID" + employeeDto.getId()));

            employee.setName(employeeDto.getName());
            employee.setEmail(employeeDto.getEmail());
            employee.setDepartment(employeeDto.getDepartment());
            employee.setPosition(employeeDto.getPosition());
            employee.setSalary(employeeDto.getSalary());
            employee.setDateOfJoining(employeeDto.getDateOfJoining());
        }

            employee = employeeRepo.save(employee);

            EmployeeResponse employeeResponse = EmployeeResponse.builder()
                    .id(employee.getId())
                    .name(employee.getName())
                    .email(employee.getEmail())
                    .department(employee.getDepartment())
                    .position(employee.getPosition())
                    .salary(employee.getSalary())
                    .dateOfJoining(employee.getDateOfJoining()).build();

        String message = (employeeDto.getId() == null) ? "Employee created successfully" : "Employee updated successfully";

        return new ApiResponse<>(HttpStatus.CREATED.value(), message, employeeResponse, null);
        }

    @Override
    public ApiResponse<List<EmployeeResponse>> createListOfEmployee(List<EmployeeRequest>  employeeRequest) {

        ArrayList<Employee> list = new ArrayList<>();

        for(EmployeeRequest request:employeeRequest){
        Employee employee;
        if(request.getId()==null){
            if (employeeRepo.existsByEmail(request.getEmail())){
                return  new ApiResponse<>(HttpStatus.CONFLICT.value(), "Email already exist",null,null);
            }
          employee = modelMapper.map(request, Employee.class);
            employee=Employee.builder()
                    .name(request.getName())
                    .email(request.getEmail())
                    .department(request.getDepartment())
                    .position(request.getPosition())
                    .salary(request.getSalary())
                    .dateOfJoining(request.getDateOfJoining()).build();
            list.add(employee);
        }
        else {
            employee=employeeRepo.findById(request.getId()).orElseThrow(()->new RuntimeException("Employee not found"));
            employee.setName(request.getName());
            employee.setEmail(request.getEmail());
            employee.setDepartment(request.getDepartment());
            employee.setPosition(request.getPosition());
            employee.setSalary(request.getSalary());
            employee.setDateOfJoining(request.getDateOfJoining());
            list.add(employee);
        }}

        List<Employee> employees = employeeRepo.saveAll(list);
        List<EmployeeResponse> employeeResponses = employees.stream().map(x -> modelMapper.map(x, EmployeeResponse.class)).toList();
        return new ApiResponse<>(HttpStatus.CREATED.value(),"Employees created successfully", employeeResponses,null);
    }


    @Override
    public ApiResponse<List<EmployeeResponse>> getAllEmployee() {
        List<Employee> employees = employeeRepo.findAll();
        List<EmployeeResponse> employeeResponses = employees
                .stream()
                .map(x -> modelMapper.map(x, EmployeeResponse.class))
                .toList();
        return new ApiResponse<>(HttpStatus.OK.value(), "Employee fetch successfully",employeeResponses,null);
    }

    @Override
    public ApiResponse<?> deleteEmployee(long id) {

        Employee employee = employeeRepo.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee not found" + id));
        employeeRepo.deleteById(id);
        return new ApiResponse<>(HttpStatus.OK.value(),"Employee Deleted successfully",null,null);
    }

    @Override
    public ApiResponse<Page<EmployeeResponse>> getPagableEmployee(int page, int size,String sortField,String sortDir) {

        Sort sort=sortDir.equalsIgnoreCase("asc") ?
                Sort.by(sortField).ascending():
                Sort.by(sortField).descending();
        Pageable pageable= PageRequest.of(page,size,sort);
        Page<Employee> employees = employeeRepo.findAll(pageable);
        Page<EmployeeResponse> employeeResponses = employees.map(x -> modelMapper.map(x, EmployeeResponse.class));
        return new ApiResponse<>(HttpStatus.OK.value(),"Employee fetch successfully",employeeResponses,null);
    }

    @Override
    public ApiResponse<Page<EmployeeBesicProjection>> getEmployeeProjection(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<EmployeeBesicProjection> employeeBesicProjections = employeeRepo.findAllBy(pageRequest);
        return new ApiResponse<>(HttpStatus.OK.value(), "Employee fetch successfully",employeeBesicProjections,null);
    }


}
