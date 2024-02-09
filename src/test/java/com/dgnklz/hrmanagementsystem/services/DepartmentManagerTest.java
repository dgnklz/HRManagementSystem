package com.dgnklz.hrmanagementsystem.services;

import com.dgnklz.hrmanagementsystem.cores.mapping.ModelMapperService;
import com.dgnklz.hrmanagementsystem.cores.results.DataResult;
import com.dgnklz.hrmanagementsystem.cores.results.Result;
import com.dgnklz.hrmanagementsystem.models.entities.Department;
import com.dgnklz.hrmanagementsystem.repositories.DepartmentRepository;
import com.dgnklz.hrmanagementsystem.services.conceretes.DepartmentManager;
import com.dgnklz.hrmanagementsystem.services.payloads.requests.department.CreateDepartmentRequest;
import com.dgnklz.hrmanagementsystem.services.payloads.requests.department.UpdateDepartmentRequest;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.department.CreateDepartmentResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.department.GetAllDepartmentsResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.department.GetDepartmentByIdResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.department.UpdateDepartmentResponse;
import com.dgnklz.hrmanagementsystem.services.rules.DepartmentBusinessRule;
import com.dgnklz.hrmanagementsystem.mocks.MocksFacilitator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DepartmentManagerTest {

    @Mock
    private DepartmentRepository repositoryMock;

    @InjectMocks
    private MocksFacilitator mocks;

    @Mock
    private ModelMapperService mapperMock;


    @InjectMocks
    private DepartmentManager departmentManager;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        ModelMapper modelMapperMock = mock(ModelMapper.class);

        when(mapperMock.forRequest()).thenReturn(modelMapperMock);
        when(mapperMock.forResponse()).thenReturn(modelMapperMock);

        DepartmentBusinessRule ruleMock = mock(DepartmentBusinessRule.class);

        departmentManager = new DepartmentManager(repositoryMock, mapperMock, ruleMock);
    }

    @Test
    void testAddDepartment() {
        CreateDepartmentRequest request = mocks.createDepartmentRequest();
        Department department = mocks.getDepartment();
        CreateDepartmentResponse expectedResponse = mocks.createDepartmentResponse();

        when(mapperMock.forRequest().map(request, Department.class)).thenReturn(department);
        when(repositoryMock.save(department)).thenReturn(department);
        when(mapperMock.forResponse().map(department, CreateDepartmentResponse.class)).thenReturn(expectedResponse);

        DataResult<CreateDepartmentResponse> result = departmentManager.add(request);

        assertEquals(expectedResponse, result.getData());
    }

    @Test
    void testGetAllDepartments() {
        List<Department> departments = Arrays.asList(mocks.getDepartment());
        GetAllDepartmentsResponse getAllDepartmentsResponse = mocks.getAllDepartmentsResponse();

        when(repositoryMock.findAll()).thenReturn(departments);
        when(mapperMock.forResponse().map(any(), eq(GetAllDepartmentsResponse.class))).thenReturn(getAllDepartmentsResponse);

        DataResult<List<GetAllDepartmentsResponse>> result = departmentManager.getAll();

        assertTrue(result.getData().contains(getAllDepartmentsResponse));
    }

    @Test
    void testGetAllWithEmptyDepartments() {
        List<Department> departments = new ArrayList<>();
        GetAllDepartmentsResponse getAllDepartmentsResponse = mocks.getAllDepartmentsResponse();

        when(repositoryMock.findAll()).thenReturn(departments);
        when(mapperMock.forResponse().map(any(), eq(GetAllDepartmentsResponse.class))).thenReturn(getAllDepartmentsResponse);

        DataResult<List<GetAllDepartmentsResponse>> result = departmentManager.getAll();

        assertNull(result.getData());
        assertEquals("No Data Found", result.getMessage());
    }

    @Test
    void testGetDepartmentById() {
        int id = 1;
        Department department = mocks.getDepartment();
        GetDepartmentByIdResponse expectedResponse = mocks.getDepartmentByIdResponse();

        when(repositoryMock.findById(id)).thenReturn(Optional.of(department));
        when(mapperMock.forResponse().map(department, GetDepartmentByIdResponse.class)).thenReturn(expectedResponse);

        DataResult<GetDepartmentByIdResponse> result = departmentManager.getById(id);

        assertEquals(expectedResponse, result.getData());
    }

    @Test
    void testUpdateDepartment() {
        int id = 1;
        UpdateDepartmentRequest request = mocks.updateDepartmentRequest();
        Department department = mocks.getDepartment();
        UpdateDepartmentResponse expectedResponse = mocks.updateDepartmentResponse();

        when(mapperMock.forRequest().map(request, Department.class)).thenReturn(department);
        when(repositoryMock.findById(id)).thenReturn(Optional.of(department));
        when(repositoryMock.save(department)).thenReturn(department);
        when(mapperMock.forResponse().map(department, UpdateDepartmentResponse.class)).thenReturn(expectedResponse);

        DataResult<UpdateDepartmentResponse> result = departmentManager.update(request, id);

        assertEquals(expectedResponse, result.getData());
    }

    @Test
    void testDeleteDepartmentById() {
        int id = 1;

        Result result = departmentManager.deleteById(id);

        // Verificação do resultado
        assertTrue(result.isSuccess());
        assertEquals("Deleted successfully", result.getMessage());
    }

    @Test
    void testDeleteDepartmentByName() {
        String name = "DepartmentName";
        Department department = mocks.getDepartment();

        when(repositoryMock.findByDepartmentName(name)).thenReturn(department);

        Result result = departmentManager.deleteByName(name);

        assertTrue(result.isSuccess());
        assertEquals("Deleted successfully", result.getMessage());
    }

}