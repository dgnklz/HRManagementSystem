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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// http://localhost:8080/api/employee/add
// http://localhost:8080/api/employee/getAll
// http://localhost:8080/api/employee/getEmployee/{id}
// http://localhost:8080/api/employee/update/{id}
// http://localhost:8080/api/employee/deleteById/{id}
// http://localhost:8080/api/employee/deleteByEmail/{email}

@RestController
@RequestMapping("/api/employee")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService service;


    @PostMapping("/add")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public DataResult<CreateEmployeeResponse> add(@Valid @RequestBody CreateEmployeeRequest request) {
        return service.add(request);
    }


    @GetMapping("/getAll")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public DataResult<List<GetAllEmployeesResponse>> getAll() {
        return service.getAll();
    }


    @GetMapping("/getEmployee/{id}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public DataResult<GetEmployeeByIdResponse> getEmployeeById(@PathVariable int id){
        return service.getEmployeeById(id);
    }
    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public DataResult<UpdateEmployeeResponse> update(@Valid @RequestBody UpdateEmployeeRequest request, @PathVariable int id){
        return service.update(request, id);
    }

    @DeleteMapping("/deleteById/{id}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public Result deleteById(@PathVariable int id) {
        return service.deleteById(id);
    }

    @DeleteMapping("/deleteByEmail/{email}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public Result deleteByEmail(@PathVariable String email) {
        return service.deleteByEmail(email);
    }
}
