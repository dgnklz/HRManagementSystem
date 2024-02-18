package com.dgnklz.hrmanagementsystem.services.payloads.responses.employee;

import com.dgnklz.hrmanagementsystem.models.entities.Address;
import com.dgnklz.hrmanagementsystem.models.entities.Gender;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class GetAllEmployeesResponse {
    private int id;
    private int roleId;
    private int departmentId;
    private int contractId;
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
