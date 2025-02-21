package com.desafio.ibm.techmanager.service.interfaces;

import com.desafio.ibm.techmanager.dto.UserDTO;
import com.desafio.ibm.techmanager.dto.UserResponseDTO;
import com.desafio.ibm.techmanager.model.User;

import java.util.List;

public interface UserService {
    UserResponseDTO criarUser(UserDTO user);
    UserResponseDTO getUserById(Long id);
    List<UserResponseDTO> getAllUsers();
    UserResponseDTO atualizarUser(Long id, UserDTO user);
    void deletarUser(Long id);

}
