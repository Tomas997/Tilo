package com.io25.tiloproject.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class ScheduleRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;


    @ManyToOne
    private Coach coach;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "scheduleRecord")
    private List<ScheduleItem> schedule;


}

