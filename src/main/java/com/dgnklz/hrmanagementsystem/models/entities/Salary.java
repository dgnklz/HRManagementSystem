package com.dgnklz.hrmanagementsystem.models.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

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

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="salaryDate")
    private Date salaryDate;

}
