package com.dgnklz.hrmanagementsystem.business.dto.requests.role;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateRoleRequest {

    @NotBlank(message = "can not be blanked")
    @Length(min = 3, max = 50, message = "should be between 3-50 chars")
    private String name;

    @NotBlank(message = "can not be blanked")
    @Length(min = 3, max = 50, message = "should be between 3-50 chars")
    private String position;

    @NotBlank(message = "can not be blanked")
    @Length(min = 3, max = 50, message = "should be between 3-50 chars")
    private String benefits;
}
