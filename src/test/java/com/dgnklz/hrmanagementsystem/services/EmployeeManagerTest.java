package com.dgnklz.hrmanagementsystem.services;

import com.dgnklz.hrmanagementsystem.cores.mapping.ModelMapperService;
import com.dgnklz.hrmanagementsystem.cores.results.DataResult;
import com.dgnklz.hrmanagementsystem.cores.results.Result;
import com.dgnklz.hrmanagementsystem.models.entities.Employee;
import com.dgnklz.hrmanagementsystem.repositories.EmployeeRepository;
import com.dgnklz.hrmanagementsystem.services.conceretes.EmployeeManager;
import com.dgnklz.hrmanagementsystem.services.payloads.requests.employee.CreateEmployeeRequest;
import com.dgnklz.hrmanagementsystem.services.payloads.requests.employee.UpdateEmployeeRequest;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.employee.CreateEmployeeResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.employee.GetAllEmployeesResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.employee.GetEmployeeByIdResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.employee.UpdateEmployeeResponse;
import com.dgnklz.hrmanagementsystem.services.rules.EmployeeBusinessRule;
import com.dgnklz.hrmanagementsystem.mocks.MocksFacilitator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EmployeeManagerTest {

    @Mock
    private EmployeeRepository repositoryMock;

    @Mock
    private ModelMapperService mapperMock;

    @Mock
    private EmployeeBusinessRule ruleMock;

    @InjectMocks
    private EmployeeManager employeeManager;

    @InjectMocks
    private MocksFacilitator mocks;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        ModelMapper modelMapperMock = mock(ModelMapper.class);

        when(mapperMock.forRequest()).thenReturn(modelMapperMock);
        when(mapperMock.forResponse()).thenReturn(modelMapperMock);

        EmployeeBusinessRule ruleMock = mock(EmployeeBusinessRule.class);

        employeeManager = new EmployeeManager(repositoryMock, mapperMock, ruleMock);
    }

    @Test
    void testAddEmployee() {
        CreateEmployeeRequest request = mocks.createEmployeeRequest();
        Employee employee = mocks.getEmployee();
        CreateEmployeeResponse expectedResponse = mocks.createEmployeeResponse();

        when(mapperMock.forRequest().map(request, Employee.class)).thenReturn(employee);
        when(repositoryMock.save(employee)).thenReturn(employee);
        when(mapperMock.forResponse().map(employee, CreateEmployeeResponse.class)).thenReturn(expectedResponse);

        DataResult<CreateEmployeeResponse> result = employeeManager.add(request);

        assertEquals(expectedResponse, result.getData());
    }

    @Test
    void testGetAllEmployees() {
        List<Employee> employees = Arrays.asList(mocks.getEmployee());
        List<GetAllEmployeesResponse> expectedResponses = employees.stream()
                .map(employee -> mapperMock.forResponse().map(employee, GetAllEmployeesResponse.class))
                .toList();
        when(repositoryMock.findAll()).thenReturn(employees);

        DataResult<List<GetAllEmployeesResponse>> result = employeeManager.getAll();

        assertEquals(expectedResponses, result.getData());
        assertEquals("All Employees Listed", result.getMessage());
    }

    @Test
    void testGetEmployeeById() {
        int id = 1;
        GetEmployeeByIdResponse expectedResponse = mocks.getEmployeeByIdResponse();
        Employee employee = ruleMock.getEmployeeById(id);

        when(ruleMock.getEmployeeById(id)).thenReturn(mocks.getEmployee());
        when(mapperMock.forRequest().map(employee, GetEmployeeByIdResponse.class)).thenReturn(expectedResponse);

        DataResult<GetEmployeeByIdResponse> result = employeeManager.getEmployeeById(id);

        assertEquals(expectedResponse, result.getData());
        assertEquals("Employee found by id", result.getMessage());
    }

    @Test
    void testUpdateEmployee() {
        UpdateEmployeeRequest request = mocks.updateEmployeeRequest();
        int id = 1;
        UpdateEmployeeResponse expectedResponse = mocks.updateEmployeeResponse();

        Employee employee = mocks.getEmployee();

        when(ruleMock.checkIfEmployeeExistByEmail(request.getEmail(), id)).thenReturn(null); // ou qualquer outro valor esperado

        when(mapperMock.forRequest().map(request, Employee.class)).thenReturn(employee);
        when(mapperMock.forResponse().map(employee, UpdateEmployeeResponse.class)).thenReturn(expectedResponse);

        DataResult<UpdateEmployeeResponse> result = employeeManager.update(request, id);

        assertEquals(expectedResponse, result.getData());
        assertEquals("Updated Successfully", result.getMessage());
    }

    @Test
    void testDeleteEmployeeById() {
        int id = 1;

        Result result = employeeManager.deleteById(id);

        assertTrue(result instanceof Result);
        assertEquals("Deleted Employee by id successfully", result.getMessage());
    }

    @Test
    void testDeleteEmployeeByEmail() {
        String email = "test@example.com";

        Result result = employeeManager.deleteByEmail(email);

        assertTrue(result instanceof Result);
        assertEquals("Deleted Employee by email successfully", result.getMessage());
    }

}