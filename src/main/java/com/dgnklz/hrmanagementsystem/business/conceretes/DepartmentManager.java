package com.dgnklz.hrmanagementsystem.business.conceretes;

import com.dgnklz.hrmanagementsystem.business.abstracts.DepartmentService;
import com.dgnklz.hrmanagementsystem.business.dto.requests.department.CreateDepartmentRequest;
import com.dgnklz.hrmanagementsystem.business.dto.responses.CreateDepartmentResponse;
import com.dgnklz.hrmanagementsystem.core.mapping.ModelMapperService;
import com.dgnklz.hrmanagementsystem.entity.Department;
import com.dgnklz.hrmanagementsystem.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.hibernate.mapping.DependantBasicValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentManager implements DepartmentService {
    @Autowired
    private DepartmentRepository repository;
    private ModelMapperService mapper;

    @Override
    public CreateDepartmentResponse add(CreateDepartmentRequest request) {
        try {
            checkIfDepartmentExistByName(request.getDepartmentName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        /*Department department = new Department();
        department.setDepartmentName(request.getDepartmentName());
        department.setDescription(request.getDescription());*/
        Department department = mapper.forRequest().map(request, Department.class);

        repository.save(department);

        /*CreateDepartmentResponse response = new CreateDepartmentResponse();
        response.setDepartmentName(department.getDepartmentName());
        response.setDescription(department.getDescription());*/
        CreateDepartmentResponse response = mapper.forResponse().map(department, CreateDepartmentResponse.class);

        return response;
    }

    /// DOMAIN RULES \\\

    private void checkIfDepartmentExistByName(String name) throws Exception {
        if(repository.existsByDepartmentName(name)){
            throw new Exception("Department exist");
        }
    }
}
