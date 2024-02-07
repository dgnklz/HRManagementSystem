package com.dgnklz.hrmanagementsystem.services.payloads.responses.department;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateDepartmentResponse {
    private int id;
    private String departmentName;
    private String description;
}
