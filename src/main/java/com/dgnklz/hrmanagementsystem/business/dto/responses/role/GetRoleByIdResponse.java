package com.dgnklz.hrmanagementsystem.business.dto.responses.role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetRoleByIdResponse {
    private String name;
    private String position;
    private String benefits;
}
