package com.dgnklz.hrmanagementsystem.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "departments")
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private int id;

    @Column(name = "departmentName")
    private String departmentName;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "department",
            fetch = FetchType.LAZY)
    private List<Employee> employees;
}
