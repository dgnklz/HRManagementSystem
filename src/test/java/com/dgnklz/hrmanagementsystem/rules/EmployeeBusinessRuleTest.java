package com.dgnklz.hrmanagementsystem.rules;
import com.dgnklz.hrmanagementsystem.cores.exceptions.types.BusinessException;
import com.dgnklz.hrmanagementsystem.models.entities.Employee;
import com.dgnklz.hrmanagementsystem.models.entities.Gender;
import com.dgnklz.hrmanagementsystem.repositories.DepartmentRepository;
import com.dgnklz.hrmanagementsystem.repositories.EmployeeRepository;
import com.dgnklz.hrmanagementsystem.repositories.RoleRepository;
import com.dgnklz.hrmanagementsystem.services.rules.EmployeeBusinessRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class EmployeeBusinessRuleTest {

    @Mock
    private EmployeeRepository employeeRepositoryMock;

    @Mock
    private DepartmentRepository departmentRepositoryMock;

    @Mock
    private RoleRepository roleRepositoryMock;

    @InjectMocks
    private EmployeeBusinessRule employeeBusinessRule;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCheckIfEmployeeNotExistById() {

        int employeeId = 1;
        when(employeeRepositoryMock.existsById(employeeId)).thenReturn(false);

        assertThrows(BusinessException.class, () -> employeeBusinessRule.checkIfEmployeeNotExistById(employeeId));
    }

    @Test
    void testCheckIfExistEmployee() {

        String name = "John";
        String surname = "Doe";
        when(employeeRepositoryMock.existsEmployeeByNameAndSurname(name, surname)).thenReturn(true);

        assertThrows(BusinessException.class, () -> employeeBusinessRule.checkIfExistEmployee(name, surname));
    }

    @Test
    void testGetEmployeeById() {

        int employeeId = 1;
        Employee mockEmployee = new Employee();
        when(employeeRepositoryMock.existsById(employeeId)).thenReturn(true);
        when(employeeRepositoryMock.findById(employeeId)).thenReturn(Optional.of(mockEmployee));

        assertEquals(mockEmployee, employeeBusinessRule.getEmployeeById(employeeId));
    }

    @Test
    void testCheckIfEmployeeNotExistByEmail() {

        String email = "test@example.com";
        when(employeeRepositoryMock.existsEmployeeByEmail(email)).thenReturn(false);

        assertThrows(BusinessException.class, () -> employeeBusinessRule.checkIfEmployeeNotExistByEmail(email));
    }

    @Test
    void testCheckIfEmployeeExistByEmail() {

        String email = "test@example.com";
        int id = 1;
        Employee mockEmployee = new Employee();
        mockEmployee.setEmail("another@example.com");
        when(employeeRepositoryMock.findById(id)).thenReturn(Optional.of(mockEmployee));
        when(employeeRepositoryMock.existsEmployeeByEmail(email)).thenReturn(true);

        assertThrows(BusinessException.class, () -> employeeBusinessRule.checkIfEmployeeExistByEmail(email, id));
    }

    @Test
    void testGetGender() {

        assertEquals(Gender.MALE, employeeBusinessRule.getGender("male"));
        assertEquals(Gender.MALE, employeeBusinessRule.getGender("m"));
        assertEquals(Gender.FEMALE, employeeBusinessRule.getGender("female"));
        assertEquals(Gender.FEMALE, employeeBusinessRule.getGender("f"));
        assertEquals(Gender.OTHER, employeeBusinessRule.getGender("other"));
        assertEquals(Gender.OTHER, employeeBusinessRule.getGender("x"));
    }

    @Test
    void testCheckIfEmployeeTwentyYearsOld() {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -19);
        Date date = calendar.getTime();

        assertThrows(BusinessException.class, () -> employeeBusinessRule.checkIfEmployeeTwentyYearsOld(date));
    }

    @Test
    void testCalculateAge() {

        Calendar dateOfBirth = Calendar.getInstance();
        dateOfBirth.set(2000, Calendar.JANUARY, 1);
        Calendar currentTime = Calendar.getInstance();
        currentTime.set(2022, Calendar.JANUARY, 1);

        //Check if its 24 or 22
        assertEquals(24, employeeBusinessRule.calculateAge(dateOfBirth.getTime()));
    }

    @Test
    void testCheckIfDepartmentNotExistById() {
        int departmentId = 1;
        when(departmentRepositoryMock.existsById(departmentId)).thenReturn(false);

        assertThrows(BusinessException.class, () -> employeeBusinessRule.checkIfDepartmentNotExistById(departmentId));
    }

    @Test
    void testCheckIfRoleNotExistById() {
        int roleId = 1;
        when(roleRepositoryMock.existsById(roleId)).thenReturn(false);

        assertThrows(BusinessException.class, () -> employeeBusinessRule.checkIfRoleNotExistById(roleId));
    }

}