package com.dgnklz.hrmanagementsystem.business.rules;

import com.dgnklz.hrmanagementsystem.core.exception.BusinessException;
import com.dgnklz.hrmanagementsystem.entity.Department;
import com.dgnklz.hrmanagementsystem.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DepartmentBusinessRule {
    private DepartmentRepository departmentRepository;
    public void checkIfDepartmentExistByName(String name) {
        List<Department> departments = departmentRepository.findAll();
        for (Department department : departments){
            if (department.getDepartmentName().equalsIgnoreCase(name)){
                throw new BusinessException("Department exist by name: " + name);
            }
        }
    }

    public void checkIfDepartmentNotExistByName(String name) {
        if (!departmentRepository.existsByDepartmentName(name)) {
            throw new BusinessException("Department not exist");
        }
    }

    public void checkIfDepartmentNotExistById(int id) {
        if (!departmentRepository.existsById(id)) {
            throw new BusinessException("Department not exist by Id");
        }
    }
}
