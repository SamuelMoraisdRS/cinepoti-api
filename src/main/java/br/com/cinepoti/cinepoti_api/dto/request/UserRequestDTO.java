package br.com.cinepoti.cinepoti_api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRequestDTO(

        @NotNull(message = "Email is required")
        @Email(message = "Invalid email format")
        String email,

        @NotNull(message = "Username is required")
        @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
        String username,

        @NotNull(message = "Password is required")
        @Size(min = 8, message = "Password must be at least 8 characters")
        String passwordHash,

        Long idProfile,

        String userType

) {}
