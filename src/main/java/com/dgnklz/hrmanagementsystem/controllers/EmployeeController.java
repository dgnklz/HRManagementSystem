package com.dgnklz.hrmanagementsystem.controllers;

import com.dgnklz.hrmanagementsystem.services.abstracts.EmployeeService;
import com.dgnklz.hrmanagementsystem.services.payloads.requests.employee.CreateEmployeeRequest;
import com.dgnklz.hrmanagementsystem.services.payloads.requests.employee.UpdateEmployeeRequest;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.employee.CreateEmployeeResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.employee.GetAllEmployeesResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.employee.GetEmployeeByIdResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.employee.UpdateEmployeeResponse;
import com.dgnklz.hrmanagementsystem.cores.results.DataResult;
import com.dgnklz.hrmanagementsystem.cores.results.Result;
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


    @GetMapping("/getEmployee/{id}")
    public DataResult<GetEmployeeByIdResponse> getEmployeeById(@PathVariable int id){
        return service.getEmployeeById(id);
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
