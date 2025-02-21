package com.desafio.ibm.techmanager.dto;

import com.desafio.ibm.techmanager.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class UserDTO {
    @NotBlank(message = "Nome completo é obrigatório")
    @Size(min = 3, max = 255, message = "Nome completo deve ter ter entre 3 e 255 caracteres")
    private String fullName;
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    private String email;
    @Size(max = 20, message = "Telefone deve ter no máximo 20 caracteres")
    private String phone;
    @Past(message = "Data de nascimento deve ser uma data no passado")
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

    public void UserDTO(User user) {
        this.fullName = user.getFullName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.birthDate = user.getBirthDate();
        this.userType = user.getUserType();
    }

}
