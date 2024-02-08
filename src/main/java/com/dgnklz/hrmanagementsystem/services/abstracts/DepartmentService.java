package com.dgnklz.hrmanagementsystem.services.abstracts;

import com.dgnklz.hrmanagementsystem.services.payloads.requests.department.CreateDepartmentRequest;
import com.dgnklz.hrmanagementsystem.services.payloads.requests.department.UpdateDepartmentRequest;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.department.CreateDepartmentResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.department.GetAllDepartmentsResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.department.UpdateDepartmentResponse;
import com.dgnklz.hrmanagementsystem.cores.results.DataResult;
import com.dgnklz.hrmanagementsystem.cores.results.Result;

import java.util.List;

public interface DepartmentService {
    DataResult<CreateDepartmentResponse> add(CreateDepartmentRequest request);
    DataResult<List<GetAllDepartmentsResponse>> getAll();
    DataResult<UpdateDepartmentResponse> update(UpdateDepartmentRequest request, int id);
    Result deleteById(int id);
    Result deleteByName(String name);
}
