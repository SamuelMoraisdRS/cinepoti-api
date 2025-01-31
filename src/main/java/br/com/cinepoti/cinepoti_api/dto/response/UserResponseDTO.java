package br.com.cinepoti.cinepoti_api.dto.response;

public record UserResponseDTO(
        Long id,
        String userType,
        String username,
        String email
) {}
