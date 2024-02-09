package com.dgnklz.hrmanagementsystem.services;


import com.dgnklz.hrmanagementsystem.mocks.MocksFacilitator;
import com.dgnklz.hrmanagementsystem.models.securities.User;
import com.dgnklz.hrmanagementsystem.repositories.UserRepository;
import com.dgnklz.hrmanagementsystem.services.conceretes.UserDetailsManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class UserDetailsManagerTest {

    @Mock
    private UserRepository userRepositoryMock;

    @InjectMocks
    private UserDetailsManager userDetailsManager;

    @InjectMocks
    private MocksFacilitator mocks;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testLoadUserByUsername_UserExists() {
        String username = "testUser";
        User user = mocks.getUser();
        when(userRepositoryMock.existsByUsername(username)).thenReturn(true);
        when(userRepositoryMock.findByUsername(username)).thenReturn(user);

        UserDetails userDetails = userDetailsManager.loadUserByUsername(username);

        assertEquals(user.getUsername(), userDetails.getUsername());
        assertEquals(user.getPassword(), userDetails.getPassword());
    }

    @Test
    void testLoadUserByUsername_UserDoesNotExist() {
        String username = "nonExistentUser";
        when(userRepositoryMock.existsByUsername(username)).thenReturn(false);

        assertThrows(UsernameNotFoundException.class, () -> userDetailsManager.loadUserByUsername(username));
    }
}