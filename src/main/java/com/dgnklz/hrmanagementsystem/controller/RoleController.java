package com.dgnklz.hrmanagementsystem.controller;


import com.dgnklz.hrmanagementsystem.business.abstracts.RoleService;
import com.dgnklz.hrmanagementsystem.business.dto.requests.role.CreateRoleRequest;
import com.dgnklz.hrmanagementsystem.business.dto.requests.role.UpdateRoleRequest;
import com.dgnklz.hrmanagementsystem.business.dto.responses.role.CreateRoleResponse;
import com.dgnklz.hrmanagementsystem.business.dto.responses.role.GetAllRolesResponse;
import com.dgnklz.hrmanagementsystem.business.dto.responses.role.UpdateRoleResponse;
import com.dgnklz.hrmanagementsystem.core.result.DataResult;
import com.dgnklz.hrmanagementsystem.core.result.Result;
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
