package com.dgnklz.hrmanagementsystem.business.conceretes;

import com.dgnklz.hrmanagementsystem.business.abstracts.DepartmentService;
import com.dgnklz.hrmanagementsystem.business.dto.requests.department.CreateDepartmentRequest;
import com.dgnklz.hrmanagementsystem.business.dto.responses.department.CreateDepartmentResponse;
import com.dgnklz.hrmanagementsystem.business.dto.responses.department.GetAllDepartmentsResponse;
import com.dgnklz.hrmanagementsystem.core.exception.BusinessException;
import com.dgnklz.hrmanagementsystem.core.mapping.ModelMapperService;
import com.dgnklz.hrmanagementsystem.core.result.DataResult;
import com.dgnklz.hrmanagementsystem.core.result.SuccessDataResult;
import com.dgnklz.hrmanagementsystem.entity.Department;
import com.dgnklz.hrmanagementsystem.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DepartmentManager implements DepartmentService {
    @Autowired
    private DepartmentRepository repository;
    private ModelMapperService mapper;

    @Override
    public DataResult<CreateDepartmentResponse> add(CreateDepartmentRequest request) {
        checkIfDepartmentExistByName(request.getDepartmentName());
        Department department = mapper.forRequest().map(request, Department.class);
        repository.save(department);
        CreateDepartmentResponse response = mapper.forResponse().map(department, CreateDepartmentResponse.class);
        return new SuccessDataResult<>(response, "Created");
    }

    @Override
    public DataResult<List<GetAllDepartmentsResponse>> getAll() {
        List<Department> departments = repository.findAll();
        List<GetAllDepartmentsResponse> responses = departments
                .stream()
                .map(department -> mapper.forResponse().map(department, GetAllDepartmentsResponse.class))
                .toList();
        return new SuccessDataResult<>(responses,"All Departments Listed");
    }

    /// DOMAIN RULES \\\

    private void checkIfDepartmentExistByName(String name){
        if(repository.existsByDepartmentName(name)){
            throw new BusinessException("Department exist");
        }
    }
}
