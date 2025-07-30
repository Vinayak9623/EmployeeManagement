package com.vsd.Dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeResponse {
    private Long id;
    private String name;
    private String email;
    private String department;
    private String position;
    private Double salary;
    private LocalDate dateOfJoining;
}
