package com.dgnklz.hrmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employees")
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "roleId")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "departmentId")
    private Department department;

    @OneToOne(mappedBy = "employee",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Contract contract;

    @Column(name="name")
    private String name;

    @Column(name="surname")
    private String surname;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Embedded
    private Address address;

    @Column(name="email")
    private String email;

    @Column(name="phoneNumber")
    private String phoneNumber;

    @Column(name="nationality")
    private String nationality;

    @Column(name="dateOfBirth")
    private Date dateOfBirth;

    @Column(name="age")
    private int age;
}
