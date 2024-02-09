package com.dgnklz.hrmanagementsystem.controller;

import com.dgnklz.hrmanagementsystem.controllers.DepartmentController;
import com.dgnklz.hrmanagementsystem.cores.results.DataResult;
import com.dgnklz.hrmanagementsystem.cores.results.Result;
import com.dgnklz.hrmanagementsystem.cores.results.SuccessDataResult;
import com.dgnklz.hrmanagementsystem.cores.results.SuccessResult;
import com.dgnklz.hrmanagementsystem.services.abstracts.DepartmentService;
import com.dgnklz.hrmanagementsystem.services.payloads.requests.department.CreateDepartmentRequest;
import com.dgnklz.hrmanagementsystem.services.payloads.requests.department.UpdateDepartmentRequest;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.department.CreateDepartmentResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.department.GetAllDepartmentsResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.department.GetDepartmentByIdResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.department.UpdateDepartmentResponse;
import com.dgnklz.hrmanagementsystem.mocks.MocksFacilitator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class DepartmentControllerTest {

    @Mock
    private DepartmentService departmentService;

    @InjectMocks
    private MocksFacilitator mocks;

    @InjectMocks
    private DepartmentController departmentController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddDepartment() {
        CreateDepartmentRequest request = mocks.createDepartmentRequest();

        CreateDepartmentResponse response = mocks.createDepartmentResponse();


        DataResult<CreateDepartmentResponse> createDepartmentResponseDataResult = new SuccessDataResult<>(response, "Created");
        when(departmentService.add(request)).thenReturn(createDepartmentResponseDataResult);
        DataResult<CreateDepartmentResponse> responseEntity = departmentController.add(request);
        assertEquals(response, responseEntity.getData());

    }

    @Test
    public void testUpdateDeparment() {
        int id = 1;
        UpdateDepartmentRequest request = mocks.updateDepartmentRequest();

        UpdateDepartmentResponse expectedResponse = mocks.updateDepartmentResponse();
        when(departmentService.update(request, id)).thenReturn(new SuccessDataResult<>(expectedResponse, "Updated Successfully"));

        DataResult<UpdateDepartmentResponse> result = departmentController.update(request, id);

        assertTrue(result.isSuccess());
        assertEquals(expectedResponse, result.getData());
        assertEquals("Updated Successfully", result.getMessage());
    }

    @Test
    void testGetAllDepartments() {

        GetAllDepartmentsResponse getAllDepartmentsResponse = mocks.getAllDepartmentsResponse();

        List<GetAllDepartmentsResponse> expectedResponses = Arrays.asList(getAllDepartmentsResponse);
        when(departmentService.getAll()).thenReturn(new SuccessDataResult<>(expectedResponses, "All Departments Listed"));

        DataResult<List<GetAllDepartmentsResponse>> result = departmentController.getAll();

        assertTrue(result.isSuccess());
        assertEquals(expectedResponses, result.getData());
        assertEquals("All Departments Listed", result.getMessage());
    }

    @Test
    void testDeleteDepartmentById() {
        int id = 1;

        Result expectedResult = new SuccessResult("Deleted successfully");
        when(departmentService.deleteById(id)).thenReturn(expectedResult);

        Result result = departmentController.delete(id);

        assertEquals(expectedResult, result);
    }

    @Test
    void testDeleteDepartmentByName() {
        String name = "DepartmentName";

        Result expectedResult = new SuccessResult("Deleted successfully");
        when(departmentService.deleteByName(name)).thenReturn(expectedResult);

        Result result = departmentController.deleteByName(name);

        assertEquals(expectedResult, result);
    }

    @Test
    void testGetDepartmentById() {
        int id = 1;

        GetDepartmentByIdResponse expectedResponse = mocks.getDepartmentByIdResponse();

        when(departmentService.getById(id)).thenReturn(new SuccessDataResult<>(expectedResponse, "Department found by id"));

        DataResult<GetDepartmentByIdResponse> result = departmentController.getById(id);

        assertEquals(expectedResponse, result.getData());
    }

}