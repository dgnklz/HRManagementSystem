package com.dgnklz.hrmanagementsystem.controller;

import com.dgnklz.hrmanagementsystem.business.abstracts.DepartmentService;
import com.dgnklz.hrmanagementsystem.business.dto.requests.department.CreateDepartmentRequest;
import com.dgnklz.hrmanagementsystem.business.dto.responses.department.CreateDepartmentResponse;
import com.dgnklz.hrmanagementsystem.business.dto.responses.department.GetAllDepartmentsResponse;
import com.dgnklz.hrmanagementsystem.core.result.DataResult;
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
        public DataResult<CreateDepartmentResponse> add(@Valid @RequestBody CreateDepartmentRequest request){
                return service.add(request);
        }

        @GetMapping("/getAll")
        public DataResult<List<GetAllDepartmentsResponse>> getAll(){
                return service.getAll();
        }
}
