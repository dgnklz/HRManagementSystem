package com.dgnklz.hrmanagementsystem.business.conceretes;

import com.dgnklz.hrmanagementsystem.business.abstracts.EmployeeService;
import com.dgnklz.hrmanagementsystem.business.dto.requests.employee.CreateEmployeeRequest;
import com.dgnklz.hrmanagementsystem.business.dto.requests.employee.UpdateEmployeeRequest;
import com.dgnklz.hrmanagementsystem.business.dto.responses.employee.CreateEmployeeResponse;
import com.dgnklz.hrmanagementsystem.business.dto.responses.employee.GetAllEmployeesResponse;
import com.dgnklz.hrmanagementsystem.business.dto.responses.employee.UpdateEmployeeResponse;
import com.dgnklz.hrmanagementsystem.business.rules.EmployeeBusinessRule;
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
    private EmployeeRepository repository;
    private ModelMapperService mapper;
    private EmployeeBusinessRule rule;
    @Override
    public DataResult<CreateEmployeeResponse> add(CreateEmployeeRequest request) {
        rule.checkIfExistEmployee(request.getName(), request.getSurname());
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
        rule.checkIfEmployeeNotExistById(id);
        rule.checkIfEmployeeExistByEmail(request.getEmail(), id);
        rule.checkIfEmployeeTwentyYearsOld(request.getDateOfBirth());
        rule.checkIfDepartmentNotExistById(request.getDepartmentId());
        rule.checkIfRoleNotExistById(request.getRoleId());
        Employee employee = mapper.forRequest().map(request, Employee.class);
        employee.setGender(rule.getGender(request.getGender()));
        employee.setAge(rule.calculateAge(request.getDateOfBirth()));
        employee.setId(id);
        repository.save(employee);
        UpdateEmployeeResponse response = mapper.forResponse().map(employee, UpdateEmployeeResponse.class);
        return new SuccessDataResult<>(response, "Updated Successfully");
    }

    @Override
    public Result deleteById(int id) {
        rule.checkIfEmployeeNotExistById(id);
        repository.deleteById(id);
        return new SuccessResult("Deleted Employee by id successfully");
    }

    @Override
    public Result deleteByEmail(String email){
        rule.checkIfEmployeeNotExistByEmail(email);
        Employee employee = repository.findEmployeeByEmail(email);
        repository.delete(employee);
        return new SuccessResult("Deleted Employee by email successfully");

    }
}
