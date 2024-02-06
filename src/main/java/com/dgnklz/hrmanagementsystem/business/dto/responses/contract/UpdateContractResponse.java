package com.dgnklz.hrmanagementsystem.business.dto.responses.contract;

import com.dgnklz.hrmanagementsystem.entity.Salary;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
