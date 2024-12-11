package br.com.cinepoti.cinepoti_api.model.user;

import java.util.UUID;

public record UserResponseDTO(UUID id, String userName, String email) {
}
