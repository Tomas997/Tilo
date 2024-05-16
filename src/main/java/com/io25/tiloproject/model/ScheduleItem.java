package com.io25.tiloproject.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
public class ScheduleItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String time;
    private Integer service;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ScheduleRecord scheduleRecord;
    private Integer quantity;
}
