package com.desafio.ibm.techmanager.service;

import com.desafio.ibm.techmanager.dto.UserDTO;
import com.desafio.ibm.techmanager.dto.UserResponseDTO;
import com.desafio.ibm.techmanager.model.User;
import com.desafio.ibm.techmanager.repository.UserRepository;
import com.desafio.ibm.techmanager.service.interfaces.UserService;
import com.desafio.ibm.techmanager.validator.UserValidator;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserResponseDTO criarUser(UserDTO user) {
        UserValidator.validateUser(user);
        User entity = user.build();
        User savedEntity = userRepository.save(entity);
        return new UserResponseDTO(savedEntity);
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException("Usuário não encontrado")
        );
        return new UserResponseDTO(user);
        }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(UserResponseDTO::new)
                .collect(Collectors.toList());

    }

    @Override
    public UserResponseDTO atualizarUser(Long id, UserDTO user) {
        UserValidator.validateUser(user);
        User existingUser = userRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException("Usuário não encontrado para o id: " + id));

        existingUser.setFullName(user.getFullName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPhone(user.getPhone());
        existingUser.setBirthDate(user.getBirthDate());
        existingUser.setUserType(user.getUserType());
        User updatedUser = userRepository.save(existingUser);
        return new UserResponseDTO(updatedUser);
    }

    @Override
    public void deletarUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("Usuário não encontrado com ID: " + id);
        }
        userRepository.deleteById(id);
    }

    public List<UserResponseDTO> buscarUsuarios(String param) {
        List<User> users = userRepository.buscarUsuarios(param);

        return users.stream()
                .map(UserResponseDTO::new)
                .collect(Collectors.toList());
    }
}