package com.dgnklz.hrmanagementsystem.business.conceretes;

import com.dgnklz.hrmanagementsystem.business.abstracts.DepartmentService;
import com.dgnklz.hrmanagementsystem.business.dto.requests.department.CreateDepartmentRequest;
import com.dgnklz.hrmanagementsystem.business.dto.requests.department.UpdateDepartmentRequest;
import com.dgnklz.hrmanagementsystem.business.dto.responses.department.CreateDepartmentResponse;
import com.dgnklz.hrmanagementsystem.business.dto.responses.department.GetAllDepartmentsResponse;
import com.dgnklz.hrmanagementsystem.business.dto.responses.department.UpdateDepartmentResponse;
import com.dgnklz.hrmanagementsystem.business.rules.DepartmentBusinessRule;
import com.dgnklz.hrmanagementsystem.core.exception.BusinessException;
import com.dgnklz.hrmanagementsystem.core.mapping.ModelMapperService;
import com.dgnklz.hrmanagementsystem.core.result.DataResult;
import com.dgnklz.hrmanagementsystem.core.result.Result;
import com.dgnklz.hrmanagementsystem.core.result.SuccessDataResult;
import com.dgnklz.hrmanagementsystem.core.result.SuccessResult;
import com.dgnklz.hrmanagementsystem.entity.Department;
import com.dgnklz.hrmanagementsystem.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DepartmentManager implements DepartmentService {
    private DepartmentRepository repository;
    private ModelMapperService mapper;
    private DepartmentBusinessRule rule;

    @Override
    public DataResult<CreateDepartmentResponse> add(CreateDepartmentRequest request) {
        rule.checkIfDepartmentExistByName(request.getDepartmentName());
        Department department = mapper.forRequest().map(request, Department.class);
        repository.save(department);
        CreateDepartmentResponse response = mapper.forResponse().map(department, CreateDepartmentResponse.class);
        return new SuccessDataResult<>(response, "Created");
    }

    @Override
    public DataResult<List<GetAllDepartmentsResponse>> getAll() {
        List<Department> departments = repository.findAll();
        if (departments.isEmpty()) {
            return new SuccessDataResult<>("No Data Found");
        }
        List<GetAllDepartmentsResponse> responses = departments
                .stream()
                .map(department -> mapper.forResponse().map(department, GetAllDepartmentsResponse.class))
                .toList();
        return new SuccessDataResult<>(responses, "All Departments Listed");
    }

    @Override
    public DataResult<UpdateDepartmentResponse> update(UpdateDepartmentRequest request, int id) {
        rule.checkIfDepartmentNotExistById(id);
        Department department = mapper.forRequest().map(request, Department.class);
        department.setId(id);
        repository.save(department);
        UpdateDepartmentResponse response = mapper.forResponse().map(department, UpdateDepartmentResponse.class);
        return new SuccessDataResult<>(response, "Updated Successfully");
    }

    @Override
    public Result deleteById(int id) {
        rule.checkIfDepartmentNotExistById(id);
        repository.deleteById(id);
        return new SuccessResult("Deleted successfully");
    }

    @Override
    public Result deleteByName(String name) {
        rule.checkIfDepartmentNotExistByName(name);
        Department department = repository.findByDepartmentName(name);
        repository.delete(department);
        return new SuccessResult("Deleted successfully");
    }
}
