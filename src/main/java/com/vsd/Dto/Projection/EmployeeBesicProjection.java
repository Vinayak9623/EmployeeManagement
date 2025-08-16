package com.vsd.Dto.Projection;


import java.time.LocalDate;

public interface EmployeeBesicProjection {

    String getName();
    String getEmail();
    String getDepartment();
    LocalDate getDateOfJoining();
}
