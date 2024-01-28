package com.dgnklz.hrmanagementsystem.business.dto.requests.department;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateDepartmentRequest {
    private String departmentName;
    private String description;
}
