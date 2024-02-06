package com.dgnklz.hrmanagementsystem.controller;

import com.dgnklz.hrmanagementsystem.business.abstracts.EmployeeService;
import com.dgnklz.hrmanagementsystem.business.dto.requests.employee.CreateEmployeeRequest;
import com.dgnklz.hrmanagementsystem.business.dto.requests.employee.UpdateEmployeeRequest;
import com.dgnklz.hrmanagementsystem.business.dto.responses.employee.CreateEmployeeResponse;
import com.dgnklz.hrmanagementsystem.business.dto.responses.employee.GetAllEmployeesResponse;
import com.dgnklz.hrmanagementsystem.business.dto.responses.employee.UpdateEmployeeResponse;
import com.dgnklz.hrmanagementsystem.core.result.DataResult;
import com.dgnklz.hrmanagementsystem.core.result.Result;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService service;


    @PostMapping("/add")
    public DataResult<CreateEmployeeResponse> add(@Valid @RequestBody CreateEmployeeRequest request) {
        return service.add(request);
    }

    @GetMapping("/getAll")
    public DataResult<List<GetAllEmployeesResponse>> getAll() {
        return service.getAll();
    }

    @PutMapping("/update/{id}")
    public DataResult<UpdateEmployeeResponse> update(@Valid @RequestBody UpdateEmployeeRequest request, @PathVariable int id){
        return service.update(request, id);
    }

    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable int id) {
        return service.deleteById(id);
    }

    @DeleteMapping("/deleteByEmail/{email}")
    public Result deleteByEmail(@PathVariable String email) {
        return service.deleteByEmail(email);
    }
}
