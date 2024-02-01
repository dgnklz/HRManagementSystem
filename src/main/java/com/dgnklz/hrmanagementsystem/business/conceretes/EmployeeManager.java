package com.dgnklz.hrmanagementsystem.business.conceretes;

import com.dgnklz.hrmanagementsystem.business.abstracts.EmployeeService;
import com.dgnklz.hrmanagementsystem.business.dto.requests.employee.CreateEmployeeRequest;
import com.dgnklz.hrmanagementsystem.business.dto.responses.department.CreateDepartmentResponse;
import com.dgnklz.hrmanagementsystem.business.dto.responses.employee.CreateEmployeeResponse;
import com.dgnklz.hrmanagementsystem.business.dto.responses.employee.GetAllEmployeeResponse;
import com.dgnklz.hrmanagementsystem.core.exception.BusinessException;
import com.dgnklz.hrmanagementsystem.core.mapping.ModelMapperService;
import com.dgnklz.hrmanagementsystem.core.result.DataResult;
import com.dgnklz.hrmanagementsystem.core.result.SuccessDataResult;
import com.dgnklz.hrmanagementsystem.entity.Employee;
import com.dgnklz.hrmanagementsystem.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

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
    public DataResult<List<GetAllEmployeeResponse>> getAll(GetAllEmployeeResponse request) {
        List<Employee> employees = repository.findAll();
        List<GetAllEmployeeResponse> responses = employees
                .stream()
                .map(employee -> mapper.forResponse().map(employee, GetAllEmployeeResponse.class))
                .toList();
        return new SuccessDataResult<>(responses, "All Employees Listed");

    }

    /// DOMAIN RULES \\\

    private void checkIfExistEmployee(String name, String surname) {
        if (repository.existsEmployeeByNameAndSurname(name, surname)){
            throw new BusinessException("Employee exist");
        }
    }
}
