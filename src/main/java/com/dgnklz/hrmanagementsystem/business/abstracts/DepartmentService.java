package com.dgnklz.hrmanagementsystem.business.abstracts;

import com.dgnklz.hrmanagementsystem.business.dto.requests.department.CreateDepartmentRequest;
import com.dgnklz.hrmanagementsystem.business.dto.requests.department.UpdateDepartmentRequest;
import com.dgnklz.hrmanagementsystem.business.dto.responses.department.CreateDepartmentResponse;
import com.dgnklz.hrmanagementsystem.business.dto.responses.department.GetAllDepartmentsResponse;
import com.dgnklz.hrmanagementsystem.business.dto.responses.department.UpdateDepartmentResponse;
import com.dgnklz.hrmanagementsystem.core.result.DataResult;
import com.dgnklz.hrmanagementsystem.core.result.Result;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface DepartmentService {
    DataResult<CreateDepartmentResponse> add(CreateDepartmentRequest request);
    DataResult<List<GetAllDepartmentsResponse>> getAll();
    DataResult<UpdateDepartmentResponse> update(UpdateDepartmentRequest request, int id);
    Result deleteById(int id);
    Result deleteByName(String name);
}
