package com.dgnklz.hrmanagementsystem.business.dto.responses.role;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateRoleResponse {

    private String name;

    private String position;

    private String benefits;
}