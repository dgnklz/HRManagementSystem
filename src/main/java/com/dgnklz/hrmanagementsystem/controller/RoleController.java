package com.dgnklz.hrmanagementsystem.controller;


import com.dgnklz.hrmanagementsystem.entity.Employee;
import com.dgnklz.hrmanagementsystem.entity.Role;
import com.dgnklz.hrmanagementsystem.repository.EmployeeRepository;
import com.dgnklz.hrmanagementsystem.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
@AllArgsConstructor
public class RoleController {

    @Autowired
    private RoleRepository repository;

    @PostMapping("/add")
    public Role add(@RequestBody Role role){

        return repository.save(role);
    }

}
