package com.dgnklz.hrmanagementsystem.business.dto.responses.department;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetAllDepartmentsResponse {
    //private int id;
    private String departmentName;
    private String description;
}
