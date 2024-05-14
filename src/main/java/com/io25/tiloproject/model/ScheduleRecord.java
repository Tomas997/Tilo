package com.io25.tiloproject.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Data
public class ScheduleRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private LocalTime time;

    @Column(length = 200)
    private String service;
    @Enumerated(EnumType.STRING)
    private ScheduleStatus status;

    private Integer quantity;
}

