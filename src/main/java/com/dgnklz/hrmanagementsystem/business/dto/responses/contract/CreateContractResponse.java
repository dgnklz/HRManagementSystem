package com.dgnklz.hrmanagementsystem.business.dto.responses.contract;

import com.dgnklz.hrmanagementsystem.entity.Salary;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class CreateContractResponse {
    private int id;
    private int employeeId;
    private Salary salary;
    private String typeContract;
    private int hoursAmountMonth;
    private Date startDateContract;
    private Date endDateContract;
}
