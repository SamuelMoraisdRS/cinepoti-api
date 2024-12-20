package br.com.cinepoti.cinepoti_api.service;

import br.com.cinepoti.cinepoti_api.mapper.ProfileUserMapper;
import br.com.cinepoti.cinepoti_api.model.profileUser.ProfileUser;
import br.com.cinepoti.cinepoti_api.dto.request.ProfileUserRequestDTO;
import br.com.cinepoti.cinepoti_api.dto.response.ProfileUserResponseDTO;
import br.com.cinepoti.cinepoti_api.repository.ProfileUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfileUserService {
    private final ProfileUserRepository profileUserRepository;

    @Autowired
    public ProfileUserService(ProfileUserRepository profileUserRepository) {
        this.profileUserRepository = profileUserRepository;

    }

    // Adiciona um novo ProfileUser
    public ProfileUserResponseDTO createProfileUser(ProfileUserRequestDTO profileUserRequestDTO) {
        ProfileUser profileUser = ProfileUserMapper.toEntity(profileUserRequestDTO);
        ProfileUser savedProfileUser = profileUserRepository.save(profileUser);
        return ProfileUserMapper.toResponseDTO(savedProfileUser);
    }

    // Recupera todos os ProfileUsers
    public List<ProfileUserResponseDTO> getAllProfileUsers() {
        List<ProfileUser> profileUsers = profileUserRepository.findAll();
        return profileUsers.stream()
                .map(ProfileUserMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // Recupera um ProfileUser por ID
    public ProfileUserResponseDTO getProfileUserById(Long id) {
        return profileUserRepository.findById(id)
                .map(ProfileUserMapper::toResponseDTO)
                .orElseThrow(() -> new RuntimeException("ProfileUser não encontrado com o ID: " + id));
    }

    // Atualiza um ProfileUser existente
    public ProfileUserResponseDTO updateProfileUser(Long id, ProfileUserRequestDTO profileUserRequestDTO) {
        return profileUserRepository.findById(id)
                .map(existingProfileUser -> {
                    // Atualiza os dados com base no DTO recebido
                    ProfileUserMapper.updateEntityFromDTO(profileUserRequestDTO, existingProfileUser);

                    // Salva a entidade atualizada
                    ProfileUser updatedProfileUser = profileUserRepository.save(existingProfileUser);
                    return ProfileUserMapper.toResponseDTO(updatedProfileUser);
                })
                .orElseThrow(() -> new RuntimeException("ProfileUser não encontrado com o ID: " + id));
    }

    // Deleta um ProfileUser por ID
    public void deleteProfileUserById(Long id) {
        Optional<ProfileUser> profileUserOpt = profileUserRepository.findById(id);
        if (profileUserOpt.isPresent()) {
            profileUserRepository.deleteById(id);
        } else {
            throw new RuntimeException("ProfileUser não encontrado com o ID: " + id);
        }
    }
}
