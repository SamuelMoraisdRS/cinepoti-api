package br.com.cinepoti.cinepoti_api.mapper;

import br.com.cinepoti.cinepoti_api.model.profile.Profile;
import br.com.cinepoti.cinepoti_api.model.profileUser.ProfileUser;
import br.com.cinepoti.cinepoti_api.model.user.User;
import br.com.cinepoti.cinepoti_api.dto.request.ProfileUserRequestDTO;
import br.com.cinepoti.cinepoti_api.dto.response.ProfileUserResponseDTO;

import br.com.cinepoti.cinepoti_api.dto.response.UserResponseDTO;
import br.com.cinepoti.cinepoti_api.dto.response.ProfileResponseDTO;

public class ProfileUserMapper {

    // Converte o ProfileUserRequestDTO para a entidade ProfileUser
    public static ProfileUser toEntity(ProfileUserRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        User user = UserMapper.toEntity(dto.userRequestDTO());
        Profile profile =  ProfileMapper.toEntity(dto.profileRequestDTO());

        return new ProfileUser(null, user, profile);
    }

    // Converte a entidade ProfileUser para o ProfileUserResponseDTO
    public static ProfileUserResponseDTO toResponseDTO(ProfileUser entity) {
        if (entity == null) {
            return null;
        }

        // Criando UserResponseDTO e ProfileResponseDTO a partir da entidade ProfileUser
        UserResponseDTO userResponseDTO = UserMapper.toResponseDTO(entity.getUser());

        ProfileResponseDTO profileResponseDTO = ProfileMapper.toResponseDTO(entity.getProfile());

        return new ProfileUserResponseDTO(entity.getId(), userResponseDTO, profileResponseDTO);
    }

    // Atualiza a entidade ProfileUser com os dados do ProfileUserRequestDTO
    public static void updateEntityFromDTO(ProfileUserRequestDTO dto, ProfileUser entity) {
        if (dto != null && entity != null) {
            entity.setUser(UserMapper.toEntity(dto.userRequestDTO())); // Atualizando o usuário
            entity.setProfile(ProfileMapper.toEntity(dto.profileRequestDTO())); // Atualizando o perfil
        }
    }
}
