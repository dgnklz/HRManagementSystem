package com.dgnklz.hrmanagementsystem.business.conceretes;

import com.dgnklz.hrmanagementsystem.business.abstracts.DepartmentService;
import com.dgnklz.hrmanagementsystem.business.dto.requests.department.CreateDepartmentRequest;
import com.dgnklz.hrmanagementsystem.business.dto.responses.CreateDepartmentResponse;
import com.dgnklz.hrmanagementsystem.entity.Department;
import com.dgnklz.hrmanagementsystem.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentManager implements DepartmentService {
    @Autowired
    private DepartmentRepository repository;

    @Override
    public CreateDepartmentResponse add(CreateDepartmentRequest request) {
        Department department = new Department();
        department.setDepartmentName(request.getDepartmentName());
        department.setDescription(request.getDescription());

        repository.save(department);

        CreateDepartmentResponse response = new CreateDepartmentResponse();
        response.setDepartmentName(department.getDepartmentName());
        response.setDescription(department.getDescription());

        return response;
    }
}
