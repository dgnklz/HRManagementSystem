package com.dgnklz.hrmanagementsystem.services.payloads.responses.role;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRoleResponse {
    private int id;
    private String name;
    private String position;
    private String benefits;
}
