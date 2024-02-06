package com.dgnklz.hrmanagementsystem.business.conceretes;

import com.dgnklz.hrmanagementsystem.business.abstracts.RoleService;
import com.dgnklz.hrmanagementsystem.business.dto.requests.role.CreateRoleRequest;
import com.dgnklz.hrmanagementsystem.business.dto.requests.role.UpdateRoleRequest;
import com.dgnklz.hrmanagementsystem.business.dto.responses.department.GetAllDepartmentsResponse;
import com.dgnklz.hrmanagementsystem.business.dto.responses.role.CreateRoleResponse;
import com.dgnklz.hrmanagementsystem.business.dto.responses.role.GetAllRolesResponse;
import com.dgnklz.hrmanagementsystem.business.dto.responses.role.UpdateRoleResponse;
import com.dgnklz.hrmanagementsystem.core.exception.BusinessException;
import com.dgnklz.hrmanagementsystem.core.mapping.ModelMapperService;
import com.dgnklz.hrmanagementsystem.core.result.DataResult;
import com.dgnklz.hrmanagementsystem.core.result.Result;
import com.dgnklz.hrmanagementsystem.core.result.SuccessDataResult;
import com.dgnklz.hrmanagementsystem.core.result.SuccessResult;
import com.dgnklz.hrmanagementsystem.entity.Department;
import com.dgnklz.hrmanagementsystem.entity.Role;
import com.dgnklz.hrmanagementsystem.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleManager implements RoleService {

    @Autowired
    private RoleRepository repository;
    private ModelMapperService mapper;


    @Override
    public DataResult<CreateRoleResponse> add(CreateRoleRequest request) {
        checkIfRoleExistByName(request.getName());
        Role role = mapper.forRequest().map(request, Role.class);
        repository.save(role);
        CreateRoleResponse response = mapper.forResponse().map(role, CreateRoleResponse.class);
        return new SuccessDataResult<>(response, "Created Role");
    }

    @Override
    public DataResult<List<GetAllRolesResponse>> getAll() {
        List<Role> roles = repository.findAll();
        List<GetAllRolesResponse> responses = roles
                .stream()
                .map(role -> mapper.forResponse().map(role, GetAllRolesResponse.class))
                .toList();
        return new SuccessDataResult<>(responses, "All Roles Listed");
    }

    @Override
    public DataResult<UpdateRoleResponse> update(UpdateRoleRequest request, int id) {
        Role role = mapper.forRequest().map(request, Role.class);
        role.setId(id);
        repository.save(role);
        UpdateRoleResponse response = mapper.forResponse().map(role, UpdateRoleResponse.class);
        return new SuccessDataResult<>(response, "Updated Successfully");
    }

    public Result deleteByName(String name) {
        checkIfRoleNotExistsByName(name);
        Role role = repository.findRoleByName(name);
        repository.delete(role);
        return new SuccessResult("Delete role by name");
    }


    /// DOMAIN RULES \\\

    private void checkIfRoleExistByName(String name) {
        List<Role> roles = repository.findAll();
        for (Role role : roles){
            if (role.getName().equalsIgnoreCase(name)){
                throw new BusinessException("Role exist by name: " + name);
            }
        }
    }

    private void checkIfRoleNotExistsByName(String name) {
        if (!repository.existsRoleByName(name)) {
            throw new BusinessException("Role does not exist");
        }
    }

}
