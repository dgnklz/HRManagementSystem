package com.dgnklz.hrmanagementsystem.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Salary {

    @Column(name="salaryAmountMonth")
    private int salaryAmountMonth;

    @Column(name="salaryAmountYear")
    private int salaryAmountYear;

    @Column(name="salaryDate")
    private Date salaryDate;
}
