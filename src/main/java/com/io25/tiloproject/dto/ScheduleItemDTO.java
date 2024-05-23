package com.io25.tiloproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
public class ScheduleItemDTO {
    private LocalDate date;
    private LocalTime time;
    private Long service;
    private String coachName;
}
