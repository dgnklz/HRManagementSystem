package com.dgnklz.hrmanagementsystem.services.payloads.responses.role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetAllRolesResponse {
    private int id;
    private String name;
    private String position;
    private String benefits;
}
