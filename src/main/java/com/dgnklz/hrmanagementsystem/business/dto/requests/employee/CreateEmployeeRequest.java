package com.dgnklz.hrmanagementsystem.business.dto.requests.employee;

import com.dgnklz.hrmanagementsystem.entity.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateEmployeeRequest {
//    @NotBlank(message = "can not be blanked")
    //roleId
    @NotNull
    private int role;
    @NotNull
    //departmentId
    private int department;

    @NotNull
    //contractId
    private int contract;

    @NotBlank(message = "can not be blanked")
    @Length(min = 3, max = 50, message = "should be between 3-50 chars")
    private String name;

    @NotBlank(message = "can not be blanked")
    @Length(min = 3, max = 50, message = "should be between 3-50 chars")
    private String surname;

    @NotBlank(message = "can not be blanked")

    private String gender;

//    @NotBlank(message = "can not be blanked")
    private Address address;

    @NotBlank(message = "can not be blanked")
    private String email;

    @NotBlank(message = "can not be blanked")
    @Length(min = 3, max = 50, message = "should be between 3-50 chars")
    private String phoneNumber;

    @NotBlank(message = "can not be blanked")
    @Length(min = 3, max = 50, message = "should be between 3-50 chars")
    private String nationality;

    @NotNull
    private Date dateOfBirth;

    @NotNull
    private int age;
}
