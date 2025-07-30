package com.vsd.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorResponse {
    private int status;
    private String error;
    private String message;
    private String path;
    private LocalDate timestamp;
}
