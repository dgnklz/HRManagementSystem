package com.dgnklz.hrmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "contract")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "employeeId")
    private Employee employee;

    @Embedded
    private Salary salary;

    @Column(name="typeContract")
    private  String typeContract;

    @Column(name="hoursAmountMonth")
    private  int hoursAmountMonth;

    @Column(name="startDateContract")
    private Date startDateContract;

    @Column(name="endDateContract")
    private Date endDateContract;



}
