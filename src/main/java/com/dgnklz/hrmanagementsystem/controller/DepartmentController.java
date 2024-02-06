package com.dgnklz.hrmanagementsystem.controller;

import com.dgnklz.hrmanagementsystem.business.abstracts.DepartmentService;
import com.dgnklz.hrmanagementsystem.business.dto.requests.department.CreateDepartmentRequest;
import com.dgnklz.hrmanagementsystem.business.dto.requests.department.UpdateDepartmentRequest;
import com.dgnklz.hrmanagementsystem.business.dto.responses.department.CreateDepartmentResponse;
import com.dgnklz.hrmanagementsystem.business.dto.responses.department.GetAllDepartmentsResponse;
import com.dgnklz.hrmanagementsystem.business.dto.responses.department.UpdateDepartmentResponse;
import com.dgnklz.hrmanagementsystem.core.result.DataResult;
import com.dgnklz.hrmanagementsystem.core.result.Result;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
@AllArgsConstructor
public class DepartmentController {

    private DepartmentService service;

    @PostMapping("/add")
    public DataResult<CreateDepartmentResponse> add(@Valid @RequestBody CreateDepartmentRequest request) {
        return service.add(request);
    }

    @GetMapping("/getAll")
    public DataResult<List<GetAllDepartmentsResponse>> getAll() {
        return service.getAll();
    }

    @PutMapping("/update/{id}")
    public DataResult<UpdateDepartmentResponse> update(@Valid @RequestBody UpdateDepartmentRequest request, @PathVariable int id){
        return service.update(request, id);
    }

    @DeleteMapping("/deleteById/{id}")
    public Result delete(@PathVariable int id) {
        return service.deleteById(id);
    }

    @DeleteMapping("/deleteByName/{name}")
    public Result deleteByName(@PathVariable String name) {
        return service.deleteByName(name);
    }
}
