package com.dgnklz.hrmanagementsystem.controllers;

import com.dgnklz.hrmanagementsystem.services.abstracts.DepartmentService;
import com.dgnklz.hrmanagementsystem.services.payloads.requests.department.CreateDepartmentRequest;
import com.dgnklz.hrmanagementsystem.services.payloads.requests.department.UpdateDepartmentRequest;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.contract.GetContractByIdResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.department.CreateDepartmentResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.department.GetAllDepartmentsResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.department.GetDepartmentByIdResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.department.UpdateDepartmentResponse;
import com.dgnklz.hrmanagementsystem.cores.results.DataResult;
import com.dgnklz.hrmanagementsystem.cores.results.Result;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// http://localhost:8080/api/department/add
// http://localhost:8080/api/department/get/{id}
// http://localhost:8080/api/department/getAll
// http://localhost:8080/api/department/update/{id}
// http://localhost:8080/api/department/deleteById/{id}
// http://localhost:8080/api/department/deleteByName/{name}

@RestController
@RequestMapping("/api/department")
@AllArgsConstructor
public class DepartmentController {

    private DepartmentService service;

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public DataResult<CreateDepartmentResponse> add(@Valid @RequestBody CreateDepartmentRequest request) {
        return service.add(request);
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public DataResult<GetDepartmentByIdResponse> getById(@PathVariable int id){
        return service.getById(id);
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public DataResult<List<GetAllDepartmentsResponse>> getAll() {
        return service.getAll();
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public DataResult<UpdateDepartmentResponse> update(@Valid @RequestBody UpdateDepartmentRequest request, @PathVariable int id){
        return service.update(request, id);
    }

    @DeleteMapping("/deleteById/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Result delete(@PathVariable int id) {
        return service.deleteById(id);
    }

    @DeleteMapping("/deleteByName/{name}")
    @PreAuthorize("hasRole('ADMIN')")
    public Result deleteByName(@PathVariable String name) {
        return service.deleteByName(name);
    }
}
