package com.vsd.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Letter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String letterType;
    @ManyToOne
    @JoinColumn(name = "created_by_hr_id")
    private User createdBy;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee recipient;
    private String message;
    private LocalDateTime dateTime=LocalDateTime.now();

}
