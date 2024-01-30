package com.dgnklz.hrmanagementsystem.business.dto.requests.role;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateRoleRequest {

    private String name;

    private String position;

    private String benefits;
}
