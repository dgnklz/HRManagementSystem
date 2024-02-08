package com.dgnklz.hrmanagementsystem.services.payloads.responses.contract;

import com.dgnklz.hrmanagementsystem.models.entities.Salary;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UpdateContractResponse {
    private int employeeId;
    private Salary salary;
    private String typeContract;
    private int hoursAmountMonth;
    private Date startDateContract;
    private Date endDateContract;
}
