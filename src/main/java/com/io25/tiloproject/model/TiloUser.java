package com.io25.tiloproject.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class TiloUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    @Column(unique = true)
    private String phone;
    @Column(unique = true)
    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(fetch = FetchType.EAGER)
    private List<ScheduleItem> scheduleItems;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TiloUser tiloUser = (TiloUser) o;
        return id != null && Objects.equals(id, tiloUser.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}



