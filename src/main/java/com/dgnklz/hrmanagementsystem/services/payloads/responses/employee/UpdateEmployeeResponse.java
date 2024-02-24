package com.dgnklz.hrmanagementsystem.services.payloads.responses.employee;

import com.dgnklz.hrmanagementsystem.models.entities.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UpdateEmployeeResponse {

    private String departmentId;

    private String roleId;
    private String name;
    private String surname;
    private Gender gender;
    private String address;
    private String email;
    private String phoneNumber;
    private String nationality;
    private Date dateOfBirth;
    private int age;
//    private Department department;
//    private Role role;
//    private Contract contract;
}
