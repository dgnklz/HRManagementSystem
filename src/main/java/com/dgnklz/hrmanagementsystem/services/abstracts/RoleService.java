package com.dgnklz.hrmanagementsystem.services.abstracts;

import com.dgnklz.hrmanagementsystem.services.payloads.requests.role.CreateRoleRequest;
import com.dgnklz.hrmanagementsystem.services.payloads.requests.role.UpdateRoleRequest;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.role.CreateRoleResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.role.GetAllRolesResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.role.UpdateRoleResponse;
import com.dgnklz.hrmanagementsystem.cores.results.DataResult;
import com.dgnklz.hrmanagementsystem.cores.results.Result;

import java.util.List;

public interface RoleService {

    DataResult<CreateRoleResponse> add(CreateRoleRequest request);

    DataResult<List<GetAllRolesResponse>> getAll();
    DataResult<UpdateRoleResponse> update(UpdateRoleRequest request, int id);

    Result deleteByName(String name);

}
