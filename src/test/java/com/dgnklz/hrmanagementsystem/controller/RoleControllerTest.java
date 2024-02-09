package com.dgnklz.hrmanagementsystem.controller;

import com.dgnklz.hrmanagementsystem.controllers.RoleController;
import com.dgnklz.hrmanagementsystem.cores.results.DataResult;
import com.dgnklz.hrmanagementsystem.cores.results.Result;
import com.dgnklz.hrmanagementsystem.cores.results.SuccessDataResult;
import com.dgnklz.hrmanagementsystem.cores.results.SuccessResult;
import com.dgnklz.hrmanagementsystem.services.abstracts.RoleService;
import com.dgnklz.hrmanagementsystem.services.payloads.requests.role.CreateRoleRequest;
import com.dgnklz.hrmanagementsystem.services.payloads.requests.role.UpdateRoleRequest;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.role.CreateRoleResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.role.GetAllRolesResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.role.GetRoleByIdResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.role.UpdateRoleResponse;
import com.dgnklz.hrmanagementsystem.mocks.MocksFacilitator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class RoleControllerTest {

    @Mock
    private RoleService serviceMock;

    @InjectMocks
    private MocksFacilitator mocks;

    @InjectMocks
    private RoleController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddRole() {
        CreateRoleRequest request = mocks.createRoleRequest();

        CreateRoleResponse expectedResponse = mocks.createRoleResponse();

        when(serviceMock.add(request)).thenReturn(new SuccessDataResult<>(expectedResponse, "Created Role"));

        DataResult<CreateRoleResponse> result = controller.add(request);

        assertEquals(expectedResponse, result.getData());
    }

    @Test
    void testGetAllRoles() {

        GetAllRolesResponse getAllRolesResponse = mocks.getAllRolesResponse();

        List<GetAllRolesResponse> expectedResponses = Arrays.asList(getAllRolesResponse);
        when(serviceMock.getAll()).thenReturn(new SuccessDataResult<>(expectedResponses, "All Roles Listed"));

        DataResult<List<GetAllRolesResponse>> result = controller.getAll();

        assertEquals(expectedResponses, result.getData());
    }

    @Test
    void testUpdateRole() {
        int id = 1;

        UpdateRoleRequest request = mocks.updateRoleRequest();

        UpdateRoleResponse expectedResponse = mocks.updateRoleResponse();

        when(serviceMock.update(request, id)).thenReturn(new SuccessDataResult<>(expectedResponse, "Updated Successfully"));

        DataResult<UpdateRoleResponse> result = controller.update(request, id);

        assertEquals(expectedResponse, result.getData());
    }

    @Test
    void testDeleteRoleByName() {
        String name = "RoleName";

        Result expectedResult = new SuccessResult("Deleted successfully");
        when(serviceMock.deleteByName(name)).thenReturn(expectedResult);

        Result result = controller.deleteByName(name);

        assertEquals(expectedResult, result);
    }

    @Test
    void testGetRoleById() {
        int id = 1;

        GetRoleByIdResponse expectedResponse = mocks.getRoleByIdResponse();

        when(serviceMock.getById(id)).thenReturn(new SuccessDataResult<>(expectedResponse, "Role found by id"));

        DataResult<GetRoleByIdResponse> result = controller.getById(id);

        assertEquals(expectedResponse, result.getData());
    }


}
