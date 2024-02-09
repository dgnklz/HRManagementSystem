package com.dgnklz.hrmanagementsystem.rules;

import com.dgnklz.hrmanagementsystem.cores.exceptions.types.BusinessException;
import com.dgnklz.hrmanagementsystem.models.entities.Role;
import com.dgnklz.hrmanagementsystem.repositories.RoleRepository;
import com.dgnklz.hrmanagementsystem.services.rules.RoleBusinessRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class RoleBusinessRuleTest {

    @Mock
    private RoleRepository roleRepositoryMock;

    @InjectMocks
    private RoleBusinessRule roleBusinessRule;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void testCheckIfRoleExistsByName_WithMatchingNameAndId() {
        int roleId = 1;
        String roleName = "TestRole";
        Role role = new Role();
        role.setId(roleId);
        role.setName(roleName);

        when(roleRepositoryMock.findById(roleId)).thenReturn(java.util.Optional.of(role));

        assertDoesNotThrow(() -> roleBusinessRule.checkIfRoleExistsByName(roleName, roleId));
    }

    @Test
    void testCheckIfRoleExistsByName_WithDifferentNameAndId() {
        int roleId = 1;
        String roleName = "TestRole";
        Role role = new Role();
        role.setId(roleId);
        role.setName("DifferentName");

        when(roleRepositoryMock.findById(roleId)).thenThrow(BusinessException.class);

        // Verifica se uma BusinessException é lançada quando o nome e o ID não correspondem
        BusinessException exception = assertThrows(BusinessException.class, () -> roleBusinessRule.checkIfRoleExistsByName(roleName, roleId));

        assertEquals(BusinessException.class, exception.getClass());
    }

    @Test
    void testCheckIfRoleExistsByNameWithDifferentId() {

        int roleId = 1;
        String roleName = "TestRole";
        Role role = new Role();
        role.setId(roleId);
        role.setName(roleName);
        when(roleRepositoryMock.findById(roleId)).thenReturn(Optional.of(role));

        boolean existsById = roleRepositoryMock.existsById(roleId);

        assertEquals(false,  existsById);

    }

    @Test
    void testCheckIfRoleNotExistsByName() {

        String roleName = "TestRole";
        when(roleRepositoryMock.existsRoleByName(roleName)).thenReturn(false);

        assertThrows(BusinessException.class, () -> roleBusinessRule.checkIfRoleNotExistsByName(roleName));
    }

    @Test
    void testCheckIfRoleNotExistById() {

        int roleId = 1;
        when(roleRepositoryMock.existsById(roleId)).thenReturn(false);

        assertThrows(BusinessException.class, () -> roleBusinessRule.checkIfRoleNotExistById(roleId));
    }

    @Test
    void testCheckIfRoleExistsByName_RoleExists() {
        String roleName = "TestRole";
        List<Role> roles = new ArrayList<>();
        Role role = new Role();
        role.setName(roleName);
        roles.add(role);

        when(roleRepositoryMock.findAll()).thenReturn(roles);

        assertThrows(BusinessException.class, () -> roleBusinessRule.checkIfRoleExistsByName(roleName));
    }

    @Test
    void testCheckIfRoleExistsByName_RoleDoesNotExist() {
        String roleName = "NonExistentRole";
        List<Role> roles = new ArrayList<>();
        Role role = new Role();
        role.setName("OtherRole");
        roles.add(role);

        when(roleRepositoryMock.findAll()).thenReturn(roles);

        roleBusinessRule.checkIfRoleExistsByName(roleName);
    }


}