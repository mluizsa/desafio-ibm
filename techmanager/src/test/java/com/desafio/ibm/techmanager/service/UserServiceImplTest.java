package com.desafio.ibm.techmanager.service;

import com.desafio.ibm.techmanager.dto.UserDTO;
import com.desafio.ibm.techmanager.dto.UserResponseDTO;
import com.desafio.ibm.techmanager.model.User;
import com.desafio.ibm.techmanager.repository.UserRepository;
import com.desafio.ibm.techmanager.validator.UserValidator;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;
    private UserDTO userDTO;
    private UserDTO validacaoUserDTO;
    private User validacaoUser;

    @BeforeEach
    void setUp() {
        /*
        * Deixa de forma padrão um user e userDto já
        * instanciado para os devidos tratamentos dos testes
        * */
        user = User.builder()
                .id(1L)
                .fullName("John Doe")
                .email("john.doe@example.com")
                .phone("123456789")
                .build();

        userDTO = UserDTO.builder()
                .fullName("John Doe")
                .email("john.doe@example.com")
                .phone("123456789")
                .build();

    }

    /* TESTE: Criar usuarios
     * Esse teste visa testar um usuário de forma simples,
     * de forma que contemple tudo já devidamente preenchido
     * */
    @Test
    public void criarUser_DeveSalvarERetornarUserResponseDTO() {
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserResponseDTO response = userService.criarUser(userDTO);

        assertNotNull(response);
        assertEquals(user.getFullName(), response.getFullName());
        verify(userRepository, times(1)).save(any(User.class));
    }

    /* TESTE: Criar usuarios com pendência de dados
     * Esse teste visa testar um usuário de forma simples com e-mail inválido,
     * de forma que contemple tudo já devidamente preenchido
     * */
    @Test
    void criarUser_DeveLancarExcecao_QuandoUsuarioInvalido() {
        UserDTO invalidUserDTO = new UserDTO(null, "invalid-email", "", null, "");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> userService.criarUser(invalidUserDTO));

        assertTrue(exception.getMessage().contains("Email inválido"));
        verify(userRepository, never()).save(any(User.class));
    }

    /* TESTE: Buscar usuários por id
     * Esse teste visa garantir a busca de um usuario por seu id
     * */
    @Test
    public void getUserById_DeveRetornarUserResponseDTO_QuandoUsuarioExistir() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        UserResponseDTO response = userService.getUserById(1L);

        assertNotNull(response);
        assertEquals(user.getFullName(), response.getFullName());
    }

    /* TESTE: Buscar usuários por id quando não existir
     * Esse teste visa garantir a busca de um usuario por seu id e caso
     * não venha existir vai receber uma excessão de tratamento
     * */
    @Test
    public void getUserById_DeveLancarExcecao_QuandoUsuarioNaoExistir() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> userService.getUserById(1L));
    }

    /* TESTE: Buscar de todos os usuários
     * Esse teste visa garantir a busca todos os usuários
     * */
    @Test
    public void getAllUsers_DeveRetornarListaDeUsuarios() {
        when(userRepository.findAll()).thenReturn(List.of(user));

        List<UserResponseDTO> users = userService.getAllUsers();

        assertFalse(users.isEmpty());
        assertEquals(1, users.size());
    }

    /* TESTE: Buscar de todos os usuários e não contendo usuários
     * Esse teste visa garantir a busca todos os usuários e quando não houver,
     * vai ser um retorno vazio
     * */
    @Test
    public void getAllUsers_DeveRetornarListaVazia_QuandoNaoHouverUsuarios() {
        when(userRepository.findAll()).thenReturn(Collections.emptyList());

        List<UserResponseDTO> users = userService.getAllUsers();

        assertTrue(users.isEmpty());
    }

    /* TESTE: Atualizar um usuário
     * Esse teste visa garantir a atualização de um usuário
     * */
    @Test
    public void atualizarUser_DeveAtualizarEretornarUserResponseDTO() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserResponseDTO response = userService.atualizarUser(1L, userDTO);

        assertNotNull(response);
        assertEquals(user.getFullName(), response.getFullName());
    }

    /* TESTE: Atualizar um usuário e quando não existir retornar uma excessão
     * Esse teste visa garantir a atualização de um usuário e quando não houver
     * o sistema vai tratar a mensagem
     * */
    @Test
    public void atualizarUser_DeveLancarExcecao_QuandoUsuarioNaoExistir() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> userService.atualizarUser(1L, userDTO));
    }

    /* TESTE: Excluir um usuário
     * Esse teste visa garantir a exclusão de um usuário
     * */
    @Test
    public void deletarUser_DeveDeletarUsuario_QuandoExistir() {
        when(userRepository.existsById(1L)).thenReturn(true);
        doNothing().when(userRepository).deleteById(1L);

        assertDoesNotThrow(() -> userService.deletarUser(1L));
        verify(userRepository, times(1)).deleteById(1L);
    }

    /* TESTE: Excluir um usuário e quando não existir lançar uma excessão
     * Esse teste visa garantir a exclusão de um usuário e quando não houver,
     * o sistema irá tratar a excessão
     * */
    @Test
    public void deletarUser_DeveLancarExcecao_QuandoUsuarioNaoExistir() {
        when(userRepository.existsById(1L)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> userService.deletarUser(1L));
    }

}