package com.dgnklz.hrmanagementsystem.services;

import com.dgnklz.hrmanagementsystem.cores.mapping.ModelMapperService;
import com.dgnklz.hrmanagementsystem.cores.results.DataResult;
import com.dgnklz.hrmanagementsystem.cores.results.Result;
import com.dgnklz.hrmanagementsystem.models.entities.Role;
import com.dgnklz.hrmanagementsystem.repositories.RoleRepository;
import com.dgnklz.hrmanagementsystem.services.conceretes.RoleManager;
import com.dgnklz.hrmanagementsystem.services.payloads.requests.role.CreateRoleRequest;
import com.dgnklz.hrmanagementsystem.services.payloads.requests.role.UpdateRoleRequest;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.role.CreateRoleResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.role.GetAllRolesResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.role.GetRoleByIdResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.role.UpdateRoleResponse;
import com.dgnklz.hrmanagementsystem.services.rules.RoleBusinessRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class RoleManagerTest {

    @Mock
    private RoleRepository roleRepositoryMock;

    @Mock
    private ModelMapperService mapperMock;

    @Mock
    private RoleBusinessRule roleBusinessRuleMock;

    @InjectMocks
    private RoleManager roleManager;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        ModelMapper modelMapperMock = mock(ModelMapper.class);

        when(mapperMock.forRequest()).thenReturn(modelMapperMock);
        when(mapperMock.forResponse()).thenReturn(modelMapperMock);

        RoleBusinessRule ruleMock = mock(RoleBusinessRule.class);

        roleManager = new RoleManager(roleRepositoryMock, mapperMock, roleBusinessRuleMock);
    }

    @Test
    void testAddRole() {

        CreateRoleRequest request = new CreateRoleRequest();
        Role role = new Role();
        CreateRoleResponse expectedResponse = new CreateRoleResponse();
        when(mapperMock.forRequest().map(request, Role.class)).thenReturn(role);
        when(roleRepositoryMock.save(role)).thenReturn(role);
        when(mapperMock.forResponse().map(role, CreateRoleResponse.class)).thenReturn(expectedResponse);

        DataResult<CreateRoleResponse> result = roleManager.add(request);

        assertEquals(expectedResponse, result.getData());
        assertEquals("Created Role", result.getMessage());
        verify(roleBusinessRuleMock).checkIfRoleExistsByName(request.getName());
    }

    @Test
    void testGetAllRoles() {

        List<Role> roles = new ArrayList<>();
        when(roleRepositoryMock.findAll()).thenReturn(roles);
        when(mapperMock.forResponse().map(any(Role.class), eq(CreateRoleResponse.class)))
                .thenReturn(new CreateRoleResponse());

        DataResult<List<GetAllRolesResponse>> result = roleManager.getAll();

        assertEquals(0, result.getData().size());
        assertEquals("All Roles Listed", result.getMessage());
    }

    @Test
    void testGetRoleById() {

        int roleId = 1;
        Role role = new Role();
        GetRoleByIdResponse expectedResponse = new GetRoleByIdResponse();
        when(roleRepositoryMock.findById(roleId)).thenReturn(Optional.of(role));
        when(mapperMock.forResponse().map(role, GetRoleByIdResponse.class)).thenReturn(expectedResponse);

        DataResult<GetRoleByIdResponse> result = roleManager.getById(roleId);

        assertEquals(expectedResponse, result.getData());
        assertEquals("Role found by id", result.getMessage());
        verify(roleBusinessRuleMock).checkIfRoleNotExistById(roleId);
    }

    @Test
    void testUpdateRole() {

        int roleId = 1;
        UpdateRoleRequest request = new UpdateRoleRequest();
        Role role = new Role();
        UpdateRoleResponse expectedResponse = new UpdateRoleResponse();
        when(roleRepositoryMock.existsById(roleId)).thenReturn(true);
        when(mapperMock.forRequest().map(request, Role.class)).thenReturn(role);
        when(roleRepositoryMock.save(role)).thenReturn(role);
        when(mapperMock.forResponse().map(role, UpdateRoleResponse.class)).thenReturn(expectedResponse);

        DataResult<UpdateRoleResponse> result = roleManager.update(request, roleId);

        assertEquals(expectedResponse, result.getData());
        assertEquals("Updated Successfully", result.getMessage());
        verify(roleBusinessRuleMock).checkIfRoleNotExistById(roleId);
        verify(roleBusinessRuleMock).checkIfRoleExistsByName(request.getName(), roleId);
    }

    @Test
    void testUpdateRoleNotExist() {
        int roleId = 1;
        UpdateRoleRequest request = new UpdateRoleRequest();
        when(roleRepositoryMock.existsById(roleId)).thenReturn(false);
        assertThrows(Exception.class, () -> roleManager.update(request, roleId));
    }

    @Test
    void testDeleteRoleByName() {

        String roleName = "TestRole";
        Role role = new Role();
        when(roleRepositoryMock.findRoleByName(roleName)).thenReturn(role);

        Result result = roleManager.deleteByName(roleName);

        assertEquals("Delete role by name", result.getMessage());
        verify(roleBusinessRuleMock).checkIfRoleNotExistsByName(roleName);
        verify(roleRepositoryMock).delete(role);
    }


}