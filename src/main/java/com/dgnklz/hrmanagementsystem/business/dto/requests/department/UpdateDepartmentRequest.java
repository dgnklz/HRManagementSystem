package com.dgnklz.hrmanagementsystem.business.dto.requests.department;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class UpdateDepartmentRequest {
    @NotBlank(message = "can not be blanked")
    @Length(min = 3, max = 50, message = "should be between 3-50 chars")
    private String departmentName;

    @NotBlank(message = "can not be blanked")
    @Length(min = 1, max = 100, message = "should be between 3-100 chars")
    private String description;
}
