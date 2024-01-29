package com.dgnklz.hrmanagementsystem.business.abstracts;

import com.dgnklz.hrmanagementsystem.business.dto.requests.department.CreateDepartmentRequest;
import com.dgnklz.hrmanagementsystem.business.dto.responses.department.CreateDepartmentResponse;
import com.dgnklz.hrmanagementsystem.business.dto.responses.department.GetAllDepartmentsResponse;
import com.dgnklz.hrmanagementsystem.core.result.DataResult;

import java.util.List;

public interface DepartmentService {
    DataResult<CreateDepartmentResponse> add(CreateDepartmentRequest request);
    DataResult<List<GetAllDepartmentsResponse>> getAll();
}
