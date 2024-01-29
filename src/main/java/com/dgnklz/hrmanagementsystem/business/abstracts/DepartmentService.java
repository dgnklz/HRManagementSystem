package com.dgnklz.hrmanagementsystem.business.abstracts;

import com.dgnklz.hrmanagementsystem.business.dto.requests.department.CreateDepartmentRequest;
import com.dgnklz.hrmanagementsystem.business.dto.responses.CreateDepartmentResponse;
import com.dgnklz.hrmanagementsystem.core.result.DataResult;
import com.dgnklz.hrmanagementsystem.entity.Department;

public interface DepartmentService {
    DataResult<CreateDepartmentResponse> add(CreateDepartmentRequest request);
}
