package com.desafio.ibm.techmanager.dto;


import com.desafio.ibm.techmanager.model.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class UserResponseDTO {
    private String fullName;
    private String email;
    private String phone;
    private LocalDate birthDate;
    private String userType;

    public User build() {
        User user = new User();
        user.setFullName(this.getFullName());
        user.setEmail(this.getEmail());
        user.setPhone(this.getPhone());
        user.setBirthDate(this.getBirthDate());
        user.setUserType(this.getUserType());
        return user;
    }

    public UserResponseDTO(User user) {
        this.fullName = user.getFullName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.birthDate = user.getBirthDate();
        this.userType = user.getUserType();
    }
}
