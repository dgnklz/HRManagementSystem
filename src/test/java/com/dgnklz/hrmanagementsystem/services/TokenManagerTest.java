package com.dgnklz.hrmanagementsystem.services;

import com.dgnklz.hrmanagementsystem.cores.exceptions.types.TokenException;
import com.dgnklz.hrmanagementsystem.models.securities.UserDetailsImpl;
import com.dgnklz.hrmanagementsystem.services.conceretes.TokenManager;
import io.jsonwebtoken.MalformedJwtException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;

class TokenManagerTest {

    @Mock
    private Authentication authenticationMock;

    @Mock
    private UserDetailsImpl userDetailsMock;

    @InjectMocks
    private TokenManager tokenManager;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        authenticationMock = mock(Authentication.class);
        userDetailsMock = mock(UserDetailsImpl.class);

        // Definir os valores de secretKey e expirationTime usando reflection
        try {
            Field secretKeyField = TokenManager.class.getDeclaredField("secretKey");
            secretKeyField.setAccessible(true);
            secretKeyField.set(tokenManager, "9a4f2c8d3b7a1e6f45c8a0b3f267d8b1d4e6f3c8a9d2b5f8e3a9c8b5f6v8a3d9");

            Field expirationTimeField = TokenManager.class.getDeclaredField("expirationTime");
            expirationTimeField.setAccessible(true);
            expirationTimeField.set(tokenManager, 600000);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetUsername_ValidToken() {
        when(authenticationMock.getPrincipal()).thenReturn(userDetailsMock);

        String username = "testUser";
        String token = tokenManager.generateToken(authenticationMock);

        when(tokenManager.getUsername(token)).thenReturn(username);

        String extractedUsername = tokenManager.getUsername(token);

        assertNotEquals(username, extractedUsername);
    }

    @Test
    void testGetUsername_InvalidToken() {

        String invalidToken = "invalidToken";

        assertThrows(MalformedJwtException.class, () -> tokenManager.getUsername(invalidToken));
    }




    @Test
    void testGenerateToken() {
        // Configuração do teste
        String username = "testUser";
        when(authenticationMock.getPrincipal()).thenReturn(userDetailsMock);
        when(userDetailsMock.getUsername()).thenReturn(username);

        // Executar o método sob teste
        String generatedToken = tokenManager.generateToken(authenticationMock);

        // Verificar se o token foi gerado corretamente
        assertNotNull(generatedToken);
    }

    @Test
    void testValidateToken_InvalidToken() {
        // Configuração do teste
        String invalidToken = "invalidToken";

        // Executar o método sob teste e verificar se uma exceção é lançada
        assertThrows(TokenException.class, () -> tokenManager.validateToken(invalidToken));
    }


}