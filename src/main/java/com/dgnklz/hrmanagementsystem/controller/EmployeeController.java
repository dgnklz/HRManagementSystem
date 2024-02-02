package com.dgnklz.hrmanagementsystem.controller;

import com.dgnklz.hrmanagementsystem.business.abstracts.EmployeeService;
import com.dgnklz.hrmanagementsystem.business.dto.requests.employee.CreateEmployeeRequest;
import com.dgnklz.hrmanagementsystem.business.dto.responses.employee.CreateEmployeeResponse;
import com.dgnklz.hrmanagementsystem.business.dto.responses.employee.GetAllEmployeeResponse;
import com.dgnklz.hrmanagementsystem.core.result.DataResult;
import com.dgnklz.hrmanagementsystem.entity.Employee;
import com.dgnklz.hrmanagementsystem.repository.EmployeeRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService service;


    @PostMapping("/add")
    public DataResult<CreateEmployeeResponse> add(@Valid @RequestBody CreateEmployeeRequest request){
        return service.add(request);
    }

    @PostMapping("/getAll")
    public DataResult<List<GetAllEmployeeResponse>> getAll() { return service.getAll();}

    /*@GetMapping("/getAll")
    public List<Employee> getAll(){
        return null;
    }*/
}
