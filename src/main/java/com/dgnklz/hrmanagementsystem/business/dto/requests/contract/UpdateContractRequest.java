package com.dgnklz.hrmanagementsystem.business.dto.requests.contract;

import com.dgnklz.hrmanagementsystem.entity.Salary;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class UpdateContractRequest {
    @NotNull(message = "Please enter employee id")
    private int employeeId;

    @NotNull(message = "Please provide Salary Details")
    private Salary salary;

    @NotBlank(message = "Type of Contract should be entered ")
    @Length(min = 3, max = 50, message = "should be between 3-50 chars")
    private  String typeContract;

    @NotNull(message = "hours amount of month should be entered")
    private  int hoursAmountMonth;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Please enter Start Date")
    private Date startDateContract;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Please enter End Date")
    private Date endDateContract;
}
