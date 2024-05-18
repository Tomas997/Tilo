package com.io25.tiloproject.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class ScheduleWeekRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer dayOfWeek;

    @ManyToOne
    private Coach coach;
    @Transient
    private List<ScheduleWeekItem> schedule;

}
