package com.dgnklz.hrmanagementsystem.business.abstracts;

import com.dgnklz.hrmanagementsystem.business.dto.requests.employee.CreateEmployeeRequest;
import com.dgnklz.hrmanagementsystem.business.dto.responses.employee.CreateEmployeeResponse;
import com.dgnklz.hrmanagementsystem.business.dto.responses.employee.GetAllEmployeeResponse;
import com.dgnklz.hrmanagementsystem.core.result.DataResult;
import com.dgnklz.hrmanagementsystem.core.result.Result;

import java.util.List;

public interface EmployeeService {

    DataResult<CreateEmployeeResponse> add(CreateEmployeeRequest request);
    DataResult<List<GetAllEmployeeResponse>> getAll();
    Result deleteById(int id);

    Result deleteByEmail(String email);
}
