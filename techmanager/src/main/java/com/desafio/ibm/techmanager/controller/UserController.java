package com.desafio.ibm.techmanager.controller;

import com.desafio.ibm.techmanager.dto.UserDTO;
import com.desafio.ibm.techmanager.dto.UserResponseDTO;
import com.desafio.ibm.techmanager.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/search")
    public List<UserResponseDTO> buscarUsuarios(@RequestParam("param") String param) {
        return userService.buscarUsuarios(param);
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> criarUser(@Valid @RequestBody UserDTO user) {
        UserResponseDTO createdUser = userService.criarUser(user);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        UserResponseDTO user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> atualizarUser(@PathVariable Long id,
                                                         @Valid @RequestBody UserDTO user) {
        UserResponseDTO updatedUser = userService.atualizarUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUser(@PathVariable Long id) {
        userService.deletarUser(id);
        return ResponseEntity.noContent().build();
    }
}
