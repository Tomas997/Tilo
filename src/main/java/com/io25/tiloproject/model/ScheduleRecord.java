package com.io25.tiloproject.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class ScheduleRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;


    @ManyToOne
    private Coach coach;

    @OneToMany(fetch = FetchType.EAGER)
    private List<ScheduleItem> schedule;


}

