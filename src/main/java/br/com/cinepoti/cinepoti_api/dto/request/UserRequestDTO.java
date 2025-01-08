package br.com.cinepoti.cinepoti_api.dto.request;

import br.com.cinepoti.cinepoti_api.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record UserRequestDTO(
        @NotNull(message = "Username is required")
        @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
        String username,

        @NotNull(message = "Password is required")
        @Size(min = 8, message = "Password must be at least 8 characters")
        String passwordHash,

        @NotNull(message = "Email is required")
        @Email(message = "Invalid email format")
        String email,

        @Size(max = 50, message = "Name cannot exceed 50 characters")
        String name,

        String userType,

        @Pattern(regexp = "\\d{11}", message = "CPF must have 11 digits")
        @CPF
        String cpf,

        @Pattern(regexp = "\\d{10,15}", message = "Telephone must be between 10 and 15 digits")
        String phone,

        Gender gender,

        @JsonFormat(pattern = "dd-MM-yyyy")
        LocalDate birthdate
) {}
