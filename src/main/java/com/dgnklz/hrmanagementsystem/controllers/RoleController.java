package com.dgnklz.hrmanagementsystem.controllers;


import com.dgnklz.hrmanagementsystem.services.abstracts.RoleService;
import com.dgnklz.hrmanagementsystem.services.payloads.requests.role.CreateRoleRequest;
import com.dgnklz.hrmanagementsystem.services.payloads.requests.role.UpdateRoleRequest;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.role.CreateRoleResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.role.GetAllRolesResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.role.UpdateRoleResponse;
import com.dgnklz.hrmanagementsystem.cores.results.DataResult;
import com.dgnklz.hrmanagementsystem.cores.results.Result;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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

    @PutMapping("/update/{id}")
    public DataResult<UpdateRoleResponse> update(@Valid @RequestBody UpdateRoleRequest request, @PathVariable int id){
        return service.update(request, id);
    }

    @DeleteMapping("/deleteByName/{name}")
    public Result deleteByName(@PathVariable String name){return service.deleteByName(name);}
}
