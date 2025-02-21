package com.desafio.ibm.techmanager.validator;

import com.desafio.ibm.techmanager.dto.UserDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import java.util.Set;


public class UserValidator {

    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public static void validateUser(UserDTO user) {
        if (user == null) {
            throw new IllegalArgumentException("Usuário não pode ser nulo.");
        }

        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);

        if (!violations.isEmpty()) {
            StringBuilder errorMessage = new StringBuilder("Erros de validação:\n");
            for (ConstraintViolation<UserDTO> violation : violations) {
                errorMessage.append("- ")
                        .append(violation.getPropertyPath())
                        .append(": ").append(violation.getMessage()).append("\n");
            }
            throw new IllegalArgumentException(errorMessage.toString());
        }

        if (!isValidEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email inválido.");
        }
    }

    private static boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        return email != null && email.matches(regex);
    }
}