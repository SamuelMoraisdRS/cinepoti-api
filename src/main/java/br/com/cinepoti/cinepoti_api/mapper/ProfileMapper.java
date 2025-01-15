package br.com.cinepoti.cinepoti_api.mapper;

import br.com.cinepoti.cinepoti_api.dto.request.ProfileRequestDTO;
import br.com.cinepoti.cinepoti_api.dto.response.ProfileResponseDTO;
import br.com.cinepoti.cinepoti_api.enums.Gender;
import br.com.cinepoti.cinepoti_api.model.Profile;
import br.com.cinepoti.cinepoti_api.model.User;
import br.com.cinepoti.cinepoti_api.util.Converter;

public class ProfileMapper {

    // Converts ProfileRequestDTO to Profile entity
    public static Profile toEntity(ProfileRequestDTO profileRequestDTO, User user) {
        if (profileRequestDTO == null) {
            return null;
        }

        Gender gender = profileRequestDTO.gender() == null ? null :
                Converter.stringToEnum(Gender.class, profileRequestDTO.gender().toString());

        return new Profile(
                null, // ID will be auto-generated
                profileRequestDTO.birthdate(),
                gender,
                profileRequestDTO.name(),
                profileRequestDTO.cpf(),
                profileRequestDTO.phone(),
                user
        );
    }

    // Converts Profile entity to ProfileResponseDTO
    public static ProfileResponseDTO toResponseDTO(Profile profile, User user) {
        if (profile == null) {
            return null;
        }

        return new ProfileResponseDTO(
                profile.getId(),
                user.getUsername(),
                user.getEmail(),
                profile.getName(),
                profile.getCpf(),
                profile.getPhone(),
                profile.getGender() != null ? profile.getGender().toString() : "",
                profile.getBirthdate()
        );
    }
}
