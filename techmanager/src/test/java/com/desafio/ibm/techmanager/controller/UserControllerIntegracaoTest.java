package com.desafio.ibm.techmanager.controller;

import com.desafio.ibm.techmanager.dto.UserDTO;
import com.desafio.ibm.techmanager.dto.UserResponseDTO;
import com.desafio.ibm.techmanager.service.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;
import java.util.Random;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = "/db/insert_dados_testes.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/db/limpar_dados_testes.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class UserControllerIntegracaoTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private UserDTO userDTO;

    @MockBean
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        /*
        * Preparar padrão uma entidade UserDTO
        * */
        Random random = new Random();
        int randomNumber = random.nextInt(1000);

        String email = "testuser" + randomNumber + "@example.com";
        userDTO = UserDTO.builder()
                .fullName("Test User")
                .email(email)
                .phone("123456789")
                .userType("ADMIN")
                .build();
    }

    @Test
    @Transactional
    public void buscarUsuarios_DeveRetornarListaUsuarios() throws Exception {
        UserResponseDTO user1 = UserResponseDTO.builder().fullName("User 1").email("user1@email.com").build();
        UserResponseDTO user2 = UserResponseDTO.builder().fullName("User 2").email("user2@email.com").build();
        List<UserResponseDTO> users = List.of(user1, user2);

        when(userService.buscarUsuarios("Test")).thenReturn(users);

        mockMvc.perform(get("/api/users/search").param("param", "Test"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].fullName").value("User 1"))
                .andExpect(jsonPath("$[1].email").value("user2@email.com"));
    }

    @Test
    @Transactional
    public void buscarUsuarios_DeveRetornarListaUsuarios_simples() throws Exception {
        mockMvc.perform(get("/api/users/search").param("param", "Test"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @Transactional
    public void getAllUsers_DeveRetornarListaUsuarios() throws Exception {
        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}