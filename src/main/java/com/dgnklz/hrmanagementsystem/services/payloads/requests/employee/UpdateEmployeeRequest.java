package com.dgnklz.hrmanagementsystem.services.payloads.requests.employee;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class UpdateEmployeeRequest {
    @NotBlank(message = "can not be blanked")
    @Length(min = 3, max = 50, message = "should be between 3-50 chars")
    private String name;

    @NotBlank(message = "can not be blanked")
    @Length(min = 3, max = 50, message = "should be between 3-50 chars")
    private String surname;

    @NotBlank(message = "can not be blanked")
    private String gender;

    @NotNull(message = "Please provide Address Details")
    private String address;

    @NotBlank(message = "can not be blanked")
    private String email;

    @NotBlank(message = "can not be blanked")
    @Length(min = 3, max = 15, message = "should be between 3-15 chars")
    private String phoneNumber;

    @NotBlank(message = "can not be blanked")
    @Length(min = 3, max = 50, message = "should be between 3-50 chars")
    private String nationality;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "can not be null")
    private Date dateOfBirth;

    @NotNull(message = "can not be null")
    private int roleId;

    @NotNull(message = "can not be null")
    private int departmentId;
}
