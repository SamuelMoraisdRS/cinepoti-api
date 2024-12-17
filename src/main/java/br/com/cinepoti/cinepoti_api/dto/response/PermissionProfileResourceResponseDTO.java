package br.com.cinepoti.cinepoti_api.dto.response;

public record PermissionProfileResourceResponseDTO(Long id, ProfileResponseDTO profileUserResponseDTO,
                                                   ResourceResponseDTO resourceResponseDTO) {
}
