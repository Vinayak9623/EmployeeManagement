package com.vsd.Dto.response;

import com.vsd.entity.User;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class LetterResponse {
    private Long id;
    private String letterType;
    private String createdBy;
    private String recipient;
    private String message;
    private LocalDateTime dateTime=LocalDateTime.now();
}
