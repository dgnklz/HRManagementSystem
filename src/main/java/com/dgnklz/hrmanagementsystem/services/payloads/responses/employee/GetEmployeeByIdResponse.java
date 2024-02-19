package com.dgnklz.hrmanagementsystem.services.payloads.responses.employee;

import com.dgnklz.hrmanagementsystem.models.entities.*;
import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class GetEmployeeByIdResponse {
    private int id;
//    @Nullable
//    private Role role;
//
//    @Nullable
//    private Department department;
//
//    @Nullable
//    private Contract contract;

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
}
