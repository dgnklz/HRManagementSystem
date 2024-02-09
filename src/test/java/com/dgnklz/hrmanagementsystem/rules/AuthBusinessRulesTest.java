package com.dgnklz.hrmanagementsystem.rules;

import com.dgnklz.hrmanagementsystem.cores.exceptions.messages.ResponseMessage;
import com.dgnklz.hrmanagementsystem.mocks.MocksFacilitator;
import com.dgnklz.hrmanagementsystem.models.securities.UserERole;
import com.dgnklz.hrmanagementsystem.models.securities.Userrole;
import com.dgnklz.hrmanagementsystem.repositories.UserRepository;
import com.dgnklz.hrmanagementsystem.repositories.UserRoleRepository;
import com.dgnklz.hrmanagementsystem.services.rules.AuthBusinessRules;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class AuthBusinessRulesTest {

    @Mock
    private UserRepository userRepositoryMock;

    @Mock
    private UserRoleRepository userRoleRepositoryMock;

    @InjectMocks
    private AuthBusinessRules authBusinessRules;

    @InjectMocks
    private MocksFacilitator mocks;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCheckIfUserExistsByUsername_UserExists() {
        String username = "existingUsername";
        when(userRepositoryMock.existsByUsername(username)).thenReturn(true);

        ResponseEntity<?> response = authBusinessRules.checkIfUserExistsByUsername(username);

        ResponseMessage expectedMessage = new ResponseMessage("Username is already taken!");
        assertEquals(expectedMessage.getMessage(), ((ResponseMessage) response.getBody()).getMessage());
    }

    @Test
    void testCheckIfUserExistsByUsername_UserDoesNotExist() {
        String username = "nonExistingUsername";
        when(userRepositoryMock.existsByUsername(username)).thenReturn(false);

        ResponseEntity<?> response = authBusinessRules.checkIfUserExistsByUsername(username);

        assertEquals(null, response);
    }

    @Test
    void testCheckIfUserExistsByEmail_UserExists() {
        String email = "existingEmail@example.com";
        when(userRepositoryMock.existsByEmail(email)).thenReturn(true);

        ResponseEntity<?> response = authBusinessRules.checkIfUserExistsByEmail(email);

        ResponseMessage expectedMessage = new ResponseMessage("Email is already in use!");


        assertEquals(expectedMessage.getMessage(), ((ResponseMessage) response.getBody()).getMessage());
    }

    @Test
    void testCheckIfUserExistsByEmail_UserDoesNotExist() {
        String email = "nonExistingEmail@example.com";
        when(userRepositoryMock.existsByEmail(email)).thenReturn(false);

        ResponseEntity<?> response = authBusinessRules.checkIfUserExistsByEmail(email);

        assertEquals(null, response);
    }

    @Test
    void testSetUserRole_WithNullRoles() {
        Set<String> roles = null;
        Userrole moderatorRole = mocks.getUserRoleModerator();
        when(userRoleRepositoryMock.findByName(UserERole.ROLE_MODERATOR)).thenReturn(moderatorRole);

        Set<Userrole> resultRoles = authBusinessRules.setUserRole(roles);

        Set<Userrole> expectedRoles = new HashSet<>();
        expectedRoles.add(moderatorRole);
        assertEquals(expectedRoles, resultRoles);
    }
    @Test
    void testSetUserRole_WithAdminRole() {
        Set<String> roles = new HashSet<>();
        roles.add("admin");
        Userrole adminRole = mocks.getUserRoleAdmin();
        when(userRoleRepositoryMock.findByName(UserERole.ROLE_ADMIN)).thenReturn(adminRole);

        Set<Userrole> resultRoles = authBusinessRules.setUserRole(roles);

        Set<Userrole> expectedRoles = new HashSet<>();
        expectedRoles.add(adminRole);
        assertEquals(expectedRoles, resultRoles);
    }

    @Test
    void testSetUserRole_WithOtherRole() {
        Set<String> roles = new HashSet<>();
        roles.add("moderator");
        Userrole moderatorRole = mocks.getUserRoleModerator();
        when(userRoleRepositoryMock.findByName(UserERole.ROLE_MODERATOR)).thenReturn(moderatorRole);

        Set<Userrole> resultRoles = authBusinessRules.setUserRole(roles);

        Set<Userrole> expectedRoles = new HashSet<>();
        expectedRoles.add(moderatorRole);
        assertEquals(expectedRoles, resultRoles);
    }
}