package com.dgnklz.hrmanagementsystem.business.dto.responses.employee;

import com.dgnklz.hrmanagementsystem.entity.*;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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
    private Address address;
    private String email;
    private String phoneNumber;
    private String nationality;
    private Date dateOfBirth;
    private int age;
}
