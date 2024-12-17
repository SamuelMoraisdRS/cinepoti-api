package br.com.cinepoti.cinepoti_api.mapper;

import br.com.cinepoti.cinepoti_api.model.profile.Profile;
import br.com.cinepoti.cinepoti_api.dto.request.ProfileRequestDTO;
import br.com.cinepoti.cinepoti_api.dto.response.ProfileResponseDTO;

public class ProfileMapper {

    // Converte o ProfileRequestDTO para a entidade Profile
    public static Profile toEntity(ProfileRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        return new Profile(null, dto.description());
    }

    // Converte a entidade Profile para o ProfileResponseDTO
    public static ProfileResponseDTO toResponseDTO(Profile entity) {
        if (entity == null) {
            return null;
        }
        return new ProfileResponseDTO(entity.getId(),entity.getDescription());
    }
}
