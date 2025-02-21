package com.desafio.ibm.techmanager.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "usersystem")
@Setter
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usersystem_id")
    private Long id;

    @Column(name = "usersystem_full_name", nullable = false)
    private String fullName;

    @Column(name = "usersystem_email", unique = true, nullable = false)
    private String email;

    @Column(name = "usersystem_phone")
    private String phone;

    @Column(name = "usersystem_birth_date")
    private LocalDate birthDate;

    @Column(name = "usersystem_user_type", columnDefinition = "VARCHAR(100)", nullable = false)
    private String userType;

}
