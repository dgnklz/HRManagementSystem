package com.dgnklz.hrmanagementsystem.business.conceretes;

import com.dgnklz.hrmanagementsystem.business.abstracts.EmployeeService;
import com.dgnklz.hrmanagementsystem.business.dto.requests.employee.CreateEmployeeRequest;
import com.dgnklz.hrmanagementsystem.business.dto.requests.employee.UpdateEmployeeRequest;
import com.dgnklz.hrmanagementsystem.business.dto.responses.employee.CreateEmployeeResponse;
import com.dgnklz.hrmanagementsystem.business.dto.responses.employee.GetAllEmployeesResponse;
import com.dgnklz.hrmanagementsystem.business.dto.responses.employee.UpdateEmployeeResponse;
import com.dgnklz.hrmanagementsystem.core.exception.BusinessException;
import com.dgnklz.hrmanagementsystem.core.mapping.ModelMapperService;
import com.dgnklz.hrmanagementsystem.core.result.DataResult;
import com.dgnklz.hrmanagementsystem.core.result.Result;
import com.dgnklz.hrmanagementsystem.core.result.SuccessDataResult;
import com.dgnklz.hrmanagementsystem.core.result.SuccessResult;
import com.dgnklz.hrmanagementsystem.entity.Employee;
import com.dgnklz.hrmanagementsystem.entity.Gender;
import com.dgnklz.hrmanagementsystem.repository.EmployeeRepository;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeManager implements EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    private ModelMapperService mapper;
    @Override
    public DataResult<CreateEmployeeResponse> add(CreateEmployeeRequest request) {
        checkIfExistEmployee(request.getName(), request.getSurname());
        Employee employee = mapper.forRequest().map(request, Employee.class);
        repository.save(employee);
        CreateEmployeeResponse response = mapper.forResponse().map(employee, CreateEmployeeResponse.class);
        return new SuccessDataResult<>(response, "Created");
    }

    @Override
    public DataResult<List<GetAllEmployeesResponse>> getAll() {
        List<Employee> employees = repository.findAll();
        List<GetAllEmployeesResponse> responses = employees
                .stream()
                .map(employee -> mapper.forResponse().map(employee, GetAllEmployeesResponse.class))
                .toList();
        return new SuccessDataResult<>(responses, "All Employees Listed");

    }

    @Override
    public DataResult<UpdateEmployeeResponse> update(UpdateEmployeeRequest request, int id) {
        checkIfEmployeeNotExistById(id);
        checkIfEmployeeExistByEmail(request.getEmail(), id);
        checkIfEmployeeTwentyYearsOld(request.getDateOfBirth());
        Employee employee = mapper.forRequest().map(request, Employee.class);
        employee.setGender(getGender(request.getGender()));



        employee.setId(id);
        repository.save(employee);
        UpdateEmployeeResponse response = mapper.forResponse().map(employee, UpdateEmployeeResponse.class);
        return new SuccessDataResult<>(response, "Updated Successfully");
    }

    @Override
    public Result deleteById(int id) {
        checkIfEmployeeNotExistById(id);
        repository.deleteById(id);
        return new SuccessResult("Deleted Employee by id successfully");
    }

    @Override
    public Result deleteByEmail(String email){
        checkIfEmployeeNotExistByEmail(email);
        Employee employee = repository.findEmployeeByEmail(email);
        repository.delete(employee);
        return new SuccessResult("Deleted Employee by email successfully");

    }

    /// DOMAIN RULES \\\

    private void checkIfEmployeeNotExistById(int id){
        if(!repository.existsById(id)){
            throw new BusinessException("Employee is not exist by this id: " + id);
        }
    }
    private void checkIfExistEmployee(String name, String surname) {
        if (repository.existsEmployeeByNameAndSurname(name, surname)){
            throw new BusinessException("Employee is exist");
        }
    }

    private void checkIfEmployeeNotExistByEmail(String email){
        if(!repository.existsEmployeeByEmail(email)) {
            throw new BusinessException("Employee email not exist");
        }
    }

    private String checkIfEmployeeExistByEmail(String email, int id){
        Employee employee = repository.findById(id).orElseThrow();
        if (!email.equalsIgnoreCase(employee.getEmail())){
            if(repository.existsEmployeeByEmail(email)) {
                throw new BusinessException("Employee email not exist");
            }
        }else {
            return null;
        }
    }

    private Gender getGender(String gender){
        switch (gender.toUpperCase()){
            case "MALE", "M" -> {return Gender.MALE;}
            case "FEMALE", "F" -> {return Gender.FEMALE;}
            default -> {return Gender.OTHER;}
        }
    }

    private void checkIfEmployeeTwentyYearsOld(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -20);
        Date currentDateMinusTwenty = calendar.getTime();
        if(!date.before(currentDateMinusTwenty)){
            throw new BusinessException("Employee should be 20 years old");
        }
    }

    private int calculateAge(Date birthDate){
        return 0;
    }

}
