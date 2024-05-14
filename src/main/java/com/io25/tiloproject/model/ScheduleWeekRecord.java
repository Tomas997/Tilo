package com.io25.tiloproject.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class ScheduleWeekRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer dayOfWeek;

    //TODO Change to string (?)
    @ManyToOne
    private Coach coach; /////////////////////////////////////////////////////////////!!!!!!!!!!!!!!!!!!!!!
    @Transient
    private List<ScheduleWeekItem> schedule;

}
