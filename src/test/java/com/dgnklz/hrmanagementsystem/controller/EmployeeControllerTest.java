package com.dgnklz.hrmanagementsystem.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import com.dgnklz.hrmanagementsystem.cores.results.SuccessDataResult;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.employee.GetEmployeeByIdResponse;
import com.dgnklz.hrmanagementsystem.mocks.MocksFacilitator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.dgnklz.hrmanagementsystem.controllers.EmployeeController;
import com.dgnklz.hrmanagementsystem.services.abstracts.EmployeeService;
import com.dgnklz.hrmanagementsystem.services.payloads.requests.employee.CreateEmployeeRequest;
import com.dgnklz.hrmanagementsystem.services.payloads.requests.employee.UpdateEmployeeRequest;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.employee.CreateEmployeeResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.employee.GetAllEmployeesResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.employee.UpdateEmployeeResponse;
import com.dgnklz.hrmanagementsystem.cores.results.DataResult;
import com.dgnklz.hrmanagementsystem.cores.results.Result;
import com.dgnklz.hrmanagementsystem.cores.results.SuccessResult;

class EmployeeControllerTest {

    @Mock
    private EmployeeService serviceMock;

    @InjectMocks
    private MocksFacilitator mocks;

    @InjectMocks
    private EmployeeController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void testAddEmployee() {
        CreateEmployeeRequest request = mocks.createEmployeeRequest();

        CreateEmployeeResponse expectedResponse = mocks.createEmployeeResponse();

        when(serviceMock.add(request)).thenReturn(new SuccessDataResult<>(expectedResponse, "Created"));

        DataResult<CreateEmployeeResponse> result = controller.add(request);

        assertEquals(expectedResponse, result.getData());
    }

    @Test
    void testGetAllEmployees() {

        GetAllEmployeesResponse getAllEmployeesResponse = mocks.getAllEmployeesResponse();

        List<GetAllEmployeesResponse> expectedResponses = Arrays.asList(getAllEmployeesResponse);
        when(serviceMock.getAll()).thenReturn(new SuccessDataResult<>(expectedResponses, "All Employees Listed"));

        DataResult<List<GetAllEmployeesResponse>> result = controller.getAll();

        assertEquals(expectedResponses, result.getData());
    }

    @Test
    void testUpdateEmployee() {
        int id = 1;
        UpdateEmployeeRequest request = mocks.updateEmployeeRequest();

        UpdateEmployeeResponse expectedResponse = mocks.updateEmployeeResponse();

        when(serviceMock.update(request, id)).thenReturn(new SuccessDataResult<>(expectedResponse, "Updated Successfully"));

        DataResult<UpdateEmployeeResponse> result = controller.update(request, id);

        assertEquals(expectedResponse, result.getData());
    }

    @Test
    void testDeleteEmployeeById() {
        int id = 1;

        Result expectedResult = new SuccessResult("Deleted successfully");
        when(serviceMock.deleteById(id)).thenReturn(expectedResult);

        Result result = controller.deleteById(id);

        assertEquals(expectedResult, result);
    }

    @Test
    void testGetEmployeeById() {
        int id = 1;

        GetEmployeeByIdResponse expectedResponse = mocks.getEmployeeByIdResponse();

        when(serviceMock.getEmployeeById(id)).thenReturn(new SuccessDataResult<>(expectedResponse, "Employee found by id"));

        DataResult<GetEmployeeByIdResponse> result = controller.getEmployeeById(id);

        assertEquals(expectedResponse, result.getData());
    }

    @Test
    void testDeleteEmployeeByEmail() {
        String email = "example@example.com";

        Result expectedResult = new SuccessResult("Deleted successfully");
        when(serviceMock.deleteByEmail(email)).thenReturn(expectedResult);

        Result result = controller.deleteByEmail(email);

        assertEquals(expectedResult, result);
    }
}
