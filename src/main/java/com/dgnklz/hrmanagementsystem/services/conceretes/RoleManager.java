package com.dgnklz.hrmanagementsystem.services.conceretes;

import com.dgnklz.hrmanagementsystem.models.entities.Department;
import com.dgnklz.hrmanagementsystem.services.abstracts.RoleService;
import com.dgnklz.hrmanagementsystem.services.payloads.requests.role.CreateRoleRequest;
import com.dgnklz.hrmanagementsystem.services.payloads.requests.role.UpdateRoleRequest;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.department.GetDepartmentByIdResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.role.CreateRoleResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.role.GetAllRolesResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.role.GetRoleByIdResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.role.UpdateRoleResponse;
import com.dgnklz.hrmanagementsystem.services.rules.RoleBusinessRule;
import com.dgnklz.hrmanagementsystem.cores.mapping.ModelMapperService;
import com.dgnklz.hrmanagementsystem.cores.results.DataResult;
import com.dgnklz.hrmanagementsystem.cores.results.Result;
import com.dgnklz.hrmanagementsystem.cores.results.SuccessDataResult;
import com.dgnklz.hrmanagementsystem.cores.results.SuccessResult;
import com.dgnklz.hrmanagementsystem.models.entities.Role;
import com.dgnklz.hrmanagementsystem.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleManager implements RoleService {
    private RoleRepository repository;
    private ModelMapperService mapper;
    private RoleBusinessRule rule;


    @Override
    public DataResult<CreateRoleResponse> add(CreateRoleRequest request) {
        rule.checkIfRoleExistsByName(request.getName());
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
    public DataResult<GetRoleByIdResponse> getById(int id) {
        rule.checkIfRoleNotExistById(id);
        Role role = repository.findById(id).orElse(null);
        GetRoleByIdResponse response = mapper.forResponse().map(role, GetRoleByIdResponse.class);
        return new SuccessDataResult<>(response, "Role found by id");
    }

    @Override
    public DataResult<UpdateRoleResponse> update(UpdateRoleRequest request, int id) {
        rule.checkIfRoleNotExistById(id);
        rule.checkIfRoleExistsByName(request.getName(), id);
        Role role = mapper.forRequest().map(request, Role.class);
        role.setId(id);
        repository.save(role);
        UpdateRoleResponse response = mapper.forResponse().map(role, UpdateRoleResponse.class);
        return new SuccessDataResult<>(response, "Updated Successfully");
    }

    public Result deleteByName(String name) {
        rule.checkIfRoleNotExistsByName(name);
        Role role = repository.findRoleByName(name);
        repository.delete(role);
        return new SuccessResult("Delete role by name");
    }

    public Result deleteById(int id) {
        rule.checkIfRoleNotExistById(id);
        repository.deleteById(id);
        return new SuccessResult("Deleted successfully");
    }
}
