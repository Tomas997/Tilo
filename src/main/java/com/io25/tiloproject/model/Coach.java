package com.io25.tiloproject.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Coach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 70)
    private String fullName;

    @Column(length = 20)
    private String phoneNumber;

    @Column(length = 450)
    private String info;
    @Column(length = 20)
    private String imgName;
    @OneToMany(fetch = FetchType.EAGER)
    private List<ScheduleRecord> scheduleRecords;
}
