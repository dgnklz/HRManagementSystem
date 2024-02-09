package com.dgnklz.hrmanagementsystem.controller;

import com.dgnklz.hrmanagementsystem.controllers.TestController;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.department.CreateDepartmentResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.department.GetAllDepartmentsResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.department.GetDepartmentByIdResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.department.UpdateDepartmentResponse;
import com.dgnklz.hrmanagementsystem.mocks.MocksFacilitator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class TestControllerTest {

    @InjectMocks
    private TestController testController;

    @InjectMocks
    private MocksFacilitator mocks;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAllAccess() {
        String expectedResponse = "Public Content.";
        String result = testController.allAccess();
        assertEquals(expectedResponse, result);
    }

    @Test
    public void testUserAccess() {
        String expectedResponse = "User Content.";
        String result = testController.userAccess();
        assertEquals(expectedResponse, result);
    }

    @Test
    void testModeratorAccess() {
        String expectedResponse = "Moderator Board.";
        String result = testController.moderatorAccess();
        assertEquals(expectedResponse, result);
    }

    @Test
    void testAdminAccess() {
        String expectedResponse = "Admin Board.";
        String result = testController.adminAccess();
        assertEquals(expectedResponse, result);
    }
}