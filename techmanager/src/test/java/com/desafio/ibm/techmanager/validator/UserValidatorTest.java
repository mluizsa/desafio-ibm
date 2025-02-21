package com.desafio.ibm.techmanager.validator;

import com.desafio.ibm.techmanager.dto.UserDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserValidatorTest {

    @Test
    public void testeExcessaoQuandoUsuarioNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            UserValidator.validateUser(null);
        });
        assertEquals("Usuário não pode ser nulo.", exception.getMessage());
    }

    @Test
    public void testeExcessaoQuandoUsuarioEmailInvalido() {
        UserDTO user =UserDTO.builder()
                .fullName("Fullname valido")
                .email("invalido- email")
                .build();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            UserValidator.validateUser(user);
        });
        assertFalse(exception.getMessage().contains("Email inválido."));
    }

    @Test
    public void testeQuandaoNaoHouverExcessao() {
        UserDTO user = UserDTO.builder()
                .fullName("Fullname valido")
                .email("valid.email@example.com")
                .build();

        assertDoesNotThrow(() -> UserValidator.validateUser(user));
    }
}