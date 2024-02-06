package com.dgnklz.hrmanagementsystem.business.dto.responses.employee;

import com.dgnklz.hrmanagementsystem.entity.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UpdateEmployeeResponse {
    private String name;
    private String surname;
    private Gender gender;
    private Address address;
    private String email;
    private String phoneNumber;
    private String nationality;
    private Date dateOfBirth;
    private int age;
    private Department department;
    private Role role;
    private Contract contract;
}
