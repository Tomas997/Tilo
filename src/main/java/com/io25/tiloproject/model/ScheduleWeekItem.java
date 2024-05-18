package com.io25.tiloproject.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class ScheduleWeekItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String time;
    private Long service;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ScheduleWeekRecord scheduleWeekRecord;

}
