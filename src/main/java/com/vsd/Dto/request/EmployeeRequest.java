package com.vsd.Dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeRequest {
    private Long id;
    private String name;
    private String email;
    private String department;
    private String position;
    private Double salary;
    private LocalDate dateOfJoining;
}
