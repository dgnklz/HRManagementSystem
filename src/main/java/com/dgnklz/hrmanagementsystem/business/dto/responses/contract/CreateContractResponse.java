package com.dgnklz.hrmanagementsystem.business.dto.responses.contract;

import com.dgnklz.hrmanagementsystem.entity.Salary;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateContractResponse {


    private Salary salary;

    private  String typeContract;

    private  int hoursAmountMonth;

    private Date startDateContract;

    private Date endDateContract;

}
