package com.dgnklz.hrmanagementsystem.controller;


import com.dgnklz.hrmanagementsystem.business.abstracts.RoleService;
import com.dgnklz.hrmanagementsystem.business.dto.requests.role.CreateRoleRequest;
import com.dgnklz.hrmanagementsystem.business.dto.responses.role.CreateRoleResponse;
import com.dgnklz.hrmanagementsystem.business.dto.responses.role.GetAllRolesResponse;
import com.dgnklz.hrmanagementsystem.core.result.DataResult;
import com.dgnklz.hrmanagementsystem.entity.Employee;
import com.dgnklz.hrmanagementsystem.entity.Role;
import com.dgnklz.hrmanagementsystem.repository.EmployeeRepository;
import com.dgnklz.hrmanagementsystem.repository.RoleRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
@AllArgsConstructor
public class RoleController {

    private RoleService service;

    @PostMapping("/add")
    public DataResult<CreateRoleResponse> add(@Valid @RequestBody CreateRoleRequest request){
        return service.add(request);

    }

    @GetMapping("getAll")
    public DataResult<List<GetAllRolesResponse>> getAll(){return service.getAll();}

}
