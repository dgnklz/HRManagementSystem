package com.dgnklz.hrmanagementsystem.business.conceretes;

import com.dgnklz.hrmanagementsystem.business.abstracts.EmployeeService;
import com.dgnklz.hrmanagementsystem.business.dto.requests.employee.CreateEmployeeRequest;
import com.dgnklz.hrmanagementsystem.business.dto.responses.employee.CreateEmployeeResponse;
import com.dgnklz.hrmanagementsystem.business.dto.responses.employee.GetAllEmployeeResponse;
import com.dgnklz.hrmanagementsystem.core.exception.BusinessException;
import com.dgnklz.hrmanagementsystem.core.mapping.ModelMapperService;
import com.dgnklz.hrmanagementsystem.core.result.DataResult;
import com.dgnklz.hrmanagementsystem.core.result.Result;
import com.dgnklz.hrmanagementsystem.core.result.SuccessDataResult;
import com.dgnklz.hrmanagementsystem.core.result.SuccessResult;
import com.dgnklz.hrmanagementsystem.entity.Employee;
import com.dgnklz.hrmanagementsystem.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    public DataResult<List<GetAllEmployeeResponse>> getAll() {
        List<Employee> employees = repository.findAll();
        List<GetAllEmployeeResponse> responses = employees
                .stream()
                .map(employee -> mapper.forResponse().map(employee, GetAllEmployeeResponse.class))
                .toList();
        return new SuccessDataResult<>(responses, "All Employees Listed");

    }
    @Override
    public Result deleteById(int id) {
        checkIfEmployeeNotExistById(id);
        repository.deleteById(id);
        return new SuccessResult("Deleted employee Sucessfully");
    }

    @Override
    public Result deleteByEmail(String email){
        checkIfEmployeeNotExistByEmail(email);
        Employee employee = repository.findEmployeeByEmail(email);
        repository.delete(employee);
        return new SuccessResult("Delete by email");

    }

    /// DOMAIN RULES \\\

    private void checkIfEmployeeNotExistById(int id){
        if(!repository.existsById(id)){
            throw new BusinessException("Employee is not exist by this id: " + id);
        }
    }
    private void checkIfExistEmployee(String name, String surname) {
        if (repository.existsEmployeeByNameAndSurname(name, surname)){
            throw new BusinessException("Employee exist");
        }
    }

    private void checkIfEmployeeNotExistByEmail(String email){
        if(!repository.existsEmployeeByEmail(email)) {
            throw new BusinessException("Employee email not exist");
        }
    }

}
