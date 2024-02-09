package com.dgnklz.hrmanagementsystem.controllers;


import com.dgnklz.hrmanagementsystem.services.abstracts.RoleService;
import com.dgnklz.hrmanagementsystem.services.payloads.requests.role.CreateRoleRequest;
import com.dgnklz.hrmanagementsystem.services.payloads.requests.role.UpdateRoleRequest;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.contract.GetContractByIdResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.role.CreateRoleResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.role.GetAllRolesResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.role.GetRoleByIdResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.role.UpdateRoleResponse;
import com.dgnklz.hrmanagementsystem.cores.results.DataResult;
import com.dgnklz.hrmanagementsystem.cores.results.Result;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// http://localhost:8080/api/role/add
// http://localhost:8080/api/role/getAll
// http://localhost:8080/api/role/get/{id}
// http://localhost:8080/api/role/update/{id}
// http://localhost:8080/api/role/deleteByName/{name}

@RestController
@RequestMapping("/api/role")
@AllArgsConstructor
public class RoleController {

    private RoleService service;

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public DataResult<CreateRoleResponse> add(@Valid @RequestBody CreateRoleRequest request){
        return service.add(request);
    }

    @GetMapping("getAll")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public DataResult<List<GetAllRolesResponse>> getAll(){return service.getAll();}

    @GetMapping("/get/{id}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public DataResult<GetRoleByIdResponse> getById(@PathVariable int id){
        return service.getById(id);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public DataResult<UpdateRoleResponse> update(@Valid @RequestBody UpdateRoleRequest request, @PathVariable int id){
        return service.update(request, id);
    }

    @DeleteMapping("/deleteByName/{name}")
    @PreAuthorize("hasRole('ADMIN')")
    public Result deleteByName(@PathVariable String name){return service.deleteByName(name);}
}
