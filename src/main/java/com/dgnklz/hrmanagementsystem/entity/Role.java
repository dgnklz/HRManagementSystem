package com.dgnklz.hrmanagementsystem.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "role")
    private List<Employee> employees;

    @Column(name="name")
    private String name;

    @Column(name="position")
    private String position;

    @Column(name="benefits")
    private String benefits;
}
