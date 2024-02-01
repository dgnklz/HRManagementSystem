package com.dgnklz.hrmanagementsystem.business.dto.requests.contract;

import com.dgnklz.hrmanagementsystem.entity.Salary;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "can not be blanked")
    private Salary salary;
    @NotBlank(message = "can not be blanked")
    @Length(min = 3, max = 50, message = "should be between 3-50 chars")
    private  String typeContract;

    @NotBlank(message = "can not be blanked")
    @Length(min = 1, max = 50, message = "should be between 3-50 chars")
    private  int hoursAmountMonth;

    @NotBlank(message = "can not be blanked")
    @Length(min = 1, max = 50, message = "should be between 3-50 chars")
    private Date startDateContract;

    @NotBlank(message = "can not be blanked")
    @Length(min = 1, max = 50, message = "should be between 3-50 chars")
    private Date endDateContract;

}
