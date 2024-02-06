package com.dgnklz.hrmanagementsystem.business.dto.responses.department;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class UpdateDepartmentResponse {
    private int id;
    private String departmentName;
    private String description;
}
