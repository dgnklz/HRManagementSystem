package com.dgnklz.hrmanagementsystem.business.dto.responses.employee;

import com.dgnklz.hrmanagementsystem.entity.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class GetEmployeeByIdResponse {
    private int id;
    private Role role;
    private Department department;
    private Contract contract;
    private String name;
    private String surname;
    private Gender gender;
    private Address address;
    private String email;
    private String phoneNumber;
    private String nationality;
    private Date dateOfBirth;
    private int age;
}
