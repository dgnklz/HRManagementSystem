package com.dgnklz.hrmanagementsystem.rules;


import com.dgnklz.hrmanagementsystem.cores.exceptions.types.BusinessException;
import com.dgnklz.hrmanagementsystem.mocks.MocksFacilitator;
import com.dgnklz.hrmanagementsystem.repositories.ContractRepository;
import com.dgnklz.hrmanagementsystem.repositories.EmployeeRepository;
import com.dgnklz.hrmanagementsystem.services.rules.ContractBusinessRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ContractBusinessRuleTest {

    @Mock
    private ContractRepository contractRepositoryMock;

    @Mock
    private EmployeeRepository employeeRepositoryMock;

    @InjectMocks
    private ContractBusinessRule contractBusinessRule;

    @InjectMocks
    MocksFacilitator mocks;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.initMocks(this);

        ContractBusinessRule ruleMock = mock(ContractBusinessRule.class);

        contractBusinessRule = new ContractBusinessRule(contractRepositoryMock, employeeRepositoryMock);

    }
    @Test
    void testCheckIfContractNotExistById() {

        int contractId = 1;
        boolean b = contractRepositoryMock.existsContractById(contractId);

        when(contractRepositoryMock.existsContractById(contractId)).thenReturn(false);

        assertThrows(BusinessException.class, () -> contractBusinessRule.checkIfContractNotExistById(contractId));
    }

    @Test
    public void testCheckIfEmployeeNotExistById_EmployeeNotExists() {
        int employeeId = 1;
        when(employeeRepositoryMock.existsById(employeeId)).thenReturn(true);
        contractBusinessRule.checkIfEmployeeNotExistById(employeeId);
    }

    @Test
    public void testCheckIfEmployeeNotExistById_EmployeeNotExistsWithException() {
        int employeeId = 1;
        when(employeeRepositoryMock.existsById(employeeId)).thenReturn(false);
        assertThrows(BusinessException.class, () -> contractBusinessRule.checkIfEmployeeNotExistById(employeeId));
    }



}