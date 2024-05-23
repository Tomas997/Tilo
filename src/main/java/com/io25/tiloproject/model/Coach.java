package com.io25.tiloproject.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Coach extends TiloUser {
    @Column(length = 450)
    private String info;

    @Column(length = 20)
    private String imgName;

    @OneToMany(fetch = FetchType.EAGER)
    private List<ScheduleRecord> scheduleRecords;
}

