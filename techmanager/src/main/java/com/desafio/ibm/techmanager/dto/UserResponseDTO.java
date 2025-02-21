package com.desafio.ibm.techmanager.dto;


import com.desafio.ibm.techmanager.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class UserResponseDTO {
    private String fullName;
    private String email;
    private String phone;
    private LocalDate birthDate;
    private String userType;

    public User build() {
        return User.builder()
                .fullName(this.getFullName())
                .email(this.getEmail())
                .phone(this.getPhone())
                .birthDate(this.getBirthDate())
                .userType(this.getUserType())
                .build();
    }

    public UserResponseDTO(User user) {
        this.fullName = user.getFullName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.birthDate = user.getBirthDate();
        this.userType = user.getUserType();
    }
}
