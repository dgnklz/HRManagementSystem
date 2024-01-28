package com.dgnklz.hrmanagementsystem.controller;

import com.dgnklz.hrmanagementsystem.entity.Employee;
import com.dgnklz.hrmanagementsystem.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@AllArgsConstructor
public class EmployeeController {
    @Autowired
    private EmployeeRepository repository;

    @PostMapping("/add")
    public Employee add(@RequestBody Employee employee){

        return repository.save(employee);
    }

    /*@GetMapping("/getAll")
    public List<Employee> getAll(){
        return null;
    }*/
}
