package br.com.cinepoti.cinepoti_api.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AddressRequestDTO(
        @NotNull(message = "Street is required")
        @Size(min = 1, max = 100, message = "Street must be between 1 and 100 characters")
        String street,

        @NotNull(message = "City is required")
        @Size(min = 1, max = 50, message = "City must be between 1 and 50 characters")
        String city,

        @NotNull(message = "State is required")
        @Size(min = 2, max = 50, message = "State must be between 2 and 50 characters")
        String state,

        @NotNull(message = "Neighborhood is required")
        @Size(min = 1, max = 50, message = "Neighborhood must be between 1 and 50 characters")
        String neighborhood,

        @NotNull(message = "Street number is required")
        @Size(min = 1, max = 10, message = "Street number must be between 1 and 10 characters")
        String streetNumber,

        @NotNull(message = "CEP is required")
        @Size(min = 8, max = 8, message = "CEP must have 8 digits")
        String cep
) {}
