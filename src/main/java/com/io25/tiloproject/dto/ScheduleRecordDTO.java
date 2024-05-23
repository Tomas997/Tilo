package com.io25.tiloproject.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalTime;

@Data
public class ScheduleRecordDTO {
    private LocalTime time;

    private String service;
}
