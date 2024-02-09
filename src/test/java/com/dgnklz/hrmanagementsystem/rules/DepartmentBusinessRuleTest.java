package com.dgnklz.hrmanagementsystem.rules;

import com.dgnklz.hrmanagementsystem.cores.exceptions.types.BusinessException;
import com.dgnklz.hrmanagementsystem.mocks.MocksFacilitator;
import com.dgnklz.hrmanagementsystem.models.entities.Department;
import com.dgnklz.hrmanagementsystem.repositories.DepartmentRepository;
import com.dgnklz.hrmanagementsystem.services.rules.DepartmentBusinessRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DepartmentBusinessRuleTest {

    @Mock
    private DepartmentRepository departmentRepositoryMock;

    @InjectMocks
    private DepartmentBusinessRule departmentBusinessRule;

    @InjectMocks
    private MocksFacilitator mocks;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCheckIfDepartmentExistByName() {
        String departmentName = "Finance";
        List<Department> departments = new ArrayList<>();

        Department department = mocks.getDepartment();
        department.setDepartmentName("Finance");

        departments.add(department);
        when(departmentRepositoryMock.findAll()).thenReturn(departments);

        assertThrows(BusinessException.class, () -> departmentBusinessRule.checkIfDepartmentExistByName(departmentName));
    }

    @Test
    void testCheckIfDepartmentNotExistByName() {
        // Configuração do teste
        String departmentName = "Finance";
        when(departmentRepositoryMock.existsByDepartmentName(departmentName)).thenReturn(false);

        // Verificar se a exceção é lançada quando o departamento não existe
        assertThrows(BusinessException.class, () -> departmentBusinessRule.checkIfDepartmentNotExistByName(departmentName));
    }

    @Test
    void testCheckIfDepartmentNotExistById() {
        // Configuração do teste
        int departmentId = 1;
        when(departmentRepositoryMock.existsById(departmentId)).thenReturn(false);

        // Verificar se a exceção é lançada quando o departamento não existe
        assertThrows(BusinessException.class, () -> departmentBusinessRule.checkIfDepartmentNotExistById(departmentId));
    }
}