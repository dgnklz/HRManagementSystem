package com.dgnklz.hrmanagementsystem.business.dto.requests.contract;

import com.dgnklz.hrmanagementsystem.entity.Salary;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateContractRequest {

    @NotNull
    private Salary salary;
    @NotBlank(message = "can not be blanked")
    @Length(min = 3, max = 50, message = "should be between 3-50 chars")
    private  String typeContract;

    @NotNull
    private  int hoursAmountMonth;

    @NotNull
    private Date startDateContract;

    @NotNull
    private Date endDateContract;

}
