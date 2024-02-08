package com.dgnklz.hrmanagementsystem.services.payloads.responses.department;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateDepartmentResponse {
    private int id;
    private String departmentName;
    private String description;
}
