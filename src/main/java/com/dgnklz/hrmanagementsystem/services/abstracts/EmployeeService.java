package com.dgnklz.hrmanagementsystem.services.abstracts;

import com.dgnklz.hrmanagementsystem.services.payloads.requests.employee.CreateEmployeeRequest;
import com.dgnklz.hrmanagementsystem.services.payloads.requests.employee.UpdateEmployeeRequest;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.employee.CreateEmployeeResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.employee.GetAllEmployeesResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.employee.GetEmployeeByIdResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.employee.UpdateEmployeeResponse;
import com.dgnklz.hrmanagementsystem.cores.results.DataResult;
import com.dgnklz.hrmanagementsystem.cores.results.Result;

import java.util.List;

public interface EmployeeService {

    DataResult<CreateEmployeeResponse> add(CreateEmployeeRequest request);
    DataResult<List<GetAllEmployeesResponse>> getAll();

    DataResult<GetEmployeeByIdResponse> getEmployeeById(int id);
    DataResult<UpdateEmployeeResponse> update(UpdateEmployeeRequest request, int id);
    Result deleteById(int id);

    Result deleteByEmail(String email);
}
