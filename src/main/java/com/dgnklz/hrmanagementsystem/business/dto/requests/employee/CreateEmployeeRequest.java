package com.dgnklz.hrmanagementsystem.business.dto.requests.employee;

import com.dgnklz.hrmanagementsystem.entity.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateEmployeeRequest {
    @NotBlank(message = "can not be blanked")
    @Length(min = 3, max = 50, message = "should be between 3-50 chars")
    private Role role;
    @NotBlank(message = "can not be blanked")
    private Department department;

    @NotBlank(message = "can not be blanked")
    private Contract contract;

    @NotBlank(message = "can not be blanked")
    @Length(min = 3, max = 50, message = "should be between 3-50 chars")
    private String name;

    @NotBlank(message = "can not be blanked")
    @Length(min = 3, max = 50, message = "should be between 3-50 chars")
    private String surname;

    @NotBlank(message = "can not be blanked")
    private Gender gender;

    @NotBlank(message = "can not be blanked")
    private Address address;

    @NotBlank(message = "can not be blanked")
    private String email;

    @NotBlank(message = "can not be blanked")
    @Length(min = 3, max = 50, message = "should be between 3-50 chars")
    private String phoneNumber;

    @NotBlank(message = "can not be blanked")
    @Length(min = 3, max = 50, message = "should be between 3-50 chars")
    private String nationality;

    @NotBlank(message = "can not be blanked")
    @Length(min = 3, max = 50, message = "should be between 3-50 chars")
    private Date dateOfBirth;

    @NotBlank(message = "can not be blanked")
    @Length(min = 3, max = 50, message = "should be between 3-50 chars")
    private int age;
}
