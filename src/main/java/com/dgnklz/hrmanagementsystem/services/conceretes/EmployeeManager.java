package com.dgnklz.hrmanagementsystem.services.conceretes;

import com.dgnklz.hrmanagementsystem.models.entities.Department;
import com.dgnklz.hrmanagementsystem.models.entities.Role;
import com.dgnklz.hrmanagementsystem.services.abstracts.EmployeeService;
import com.dgnklz.hrmanagementsystem.services.payloads.requests.employee.CreateEmployeeRequest;
import com.dgnklz.hrmanagementsystem.services.payloads.requests.employee.UpdateEmployeeRequest;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.employee.CreateEmployeeResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.employee.GetAllEmployeesResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.employee.GetEmployeeByIdResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.employee.UpdateEmployeeResponse;
import com.dgnklz.hrmanagementsystem.services.rules.DepartmentBusinessRule;
import com.dgnklz.hrmanagementsystem.services.rules.EmployeeBusinessRule;
import com.dgnklz.hrmanagementsystem.cores.mapping.ModelMapperService;
import com.dgnklz.hrmanagementsystem.cores.results.DataResult;
import com.dgnklz.hrmanagementsystem.cores.results.Result;
import com.dgnklz.hrmanagementsystem.cores.results.SuccessDataResult;
import com.dgnklz.hrmanagementsystem.cores.results.SuccessResult;
import com.dgnklz.hrmanagementsystem.models.entities.Employee;
import com.dgnklz.hrmanagementsystem.repositories.EmployeeRepository;
import com.dgnklz.hrmanagementsystem.services.rules.RoleBusinessRule;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeManager implements EmployeeService {
    private EmployeeRepository repository;
    private ModelMapperService mapper;
    private EmployeeBusinessRule rule;

    private DepartmentBusinessRule departmentBusinessRule;
    private RoleBusinessRule roleBusinessRule;
    @Override
    public DataResult<CreateEmployeeResponse> add(CreateEmployeeRequest request) {
        rule.checkIfExistEmployee(request.getName(), request.getSurname());
        Role roleById = roleBusinessRule.getRoleById(request.getRoleId());
        Department departmentById = departmentBusinessRule.getDepartmentById(request.getDepartmentId());
        Employee employee = mapper.forRequest().map(request, Employee.class);
        employee.setDepartment(departmentById);
        employee.setRole(roleById);
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
    public DataResult<GetEmployeeByIdResponse> getEmployeeById(int id) {
        Employee employeeResult = rule.getEmployeeById(id);
        GetEmployeeByIdResponse response = mapper.forRequest().map(employeeResult, GetEmployeeByIdResponse.class);
        return new SuccessDataResult<>(response, "Employee found by id");
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
