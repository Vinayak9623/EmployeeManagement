package com.vsd.Dto.request;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class LetterRequest {
    private Long id;
    private String letterType;
    private Long HrId;
    private Long employeeId;
    private String message;
    private LocalDateTime dateTime=LocalDateTime.now();
}
