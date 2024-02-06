package com.dgnklz.hrmanagementsystem.business.abstracts;

import com.dgnklz.hrmanagementsystem.business.dto.requests.role.CreateRoleRequest;
import com.dgnklz.hrmanagementsystem.business.dto.requests.role.UpdateRoleRequest;
import com.dgnklz.hrmanagementsystem.business.dto.responses.role.CreateRoleResponse;
import com.dgnklz.hrmanagementsystem.business.dto.responses.role.GetAllRolesResponse;
import com.dgnklz.hrmanagementsystem.business.dto.responses.role.UpdateRoleResponse;
import com.dgnklz.hrmanagementsystem.core.result.DataResult;
import com.dgnklz.hrmanagementsystem.core.result.Result;

import java.util.List;

public interface RoleService {

    DataResult<CreateRoleResponse> add(CreateRoleRequest request);

    DataResult<List<GetAllRolesResponse>> getAll();
    DataResult<UpdateRoleResponse> update(UpdateRoleRequest request, int id);

    Result deleteByName(String name);

}
