package br.com.cinepoti.cinepoti_api.service;

import br.com.cinepoti.cinepoti_api.mapper.ProfileMapper;
import br.com.cinepoti.cinepoti_api.model.profile.Profile;
import br.com.cinepoti.cinepoti_api.dto.request.ProfileRequestDTO;
import br.com.cinepoti.cinepoti_api.dto.response.ProfileResponseDTO;
import br.com.cinepoti.cinepoti_api.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;


    @Autowired
    public ProfileService(ProfileRepository profileRepository ) {
        this.profileRepository = profileRepository;
    }

    // Cria um novo Profile
    public ProfileResponseDTO createProfile(ProfileRequestDTO profileRequestDTO) {
        Profile profile = ProfileMapper.toEntity(profileRequestDTO); // Usando o mapper para converter o DTO para a entidade
        profile = profileRepository.save(profile);
        return ProfileMapper.toResponseDTO(profile); // Usando o mapper para converter a entidade para o DTO de resposta
    }

    // Recupera todos os Profiles
    public List<ProfileResponseDTO> getAllProfiles() {
        List<Profile> profiles = profileRepository.findAll();
        return profiles.stream()
                .map(ProfileMapper::toResponseDTO) // Usando o mapper para converter a entidade para o DTO
                .collect(Collectors.toList());
    }

    // Recupera um Profile por ID
    public ProfileResponseDTO getProfileById(Long id) {
        Optional<Profile> profileOpt = profileRepository.findById(id);
        if (profileOpt.isPresent()) {
            Profile profile = profileOpt.get();
            return ProfileMapper.toResponseDTO(profile); // Usando o mapper para converter a entidade para o DTO
        } else {
            throw new RuntimeException("Profile not found with id: " + id);
        }
    }

    // Atualiza um Profile existente
    public ProfileResponseDTO updateProfile(Long id, ProfileRequestDTO profileRequestDTO) {
        Optional<Profile> profileOpt = profileRepository.findById(id);
        if (profileOpt.isPresent()) {
            Profile profile = profileOpt.get();
            profile.setDescription(profileRequestDTO.description()); // Atualiza os campos
            profile = profileRepository.save(profile);
            return ProfileMapper.toResponseDTO(profile); // Usando o mapper para converter a entidade para o DTO
        } else {
            throw new RuntimeException("Profile not found with id: " + id);
        }
    }

    // Deleta um Profile por ID
    public void deleteProfile(Long id) {
        Optional<Profile> profileOpt = profileRepository.findById(id);
        if (profileOpt.isPresent()) {
            profileRepository.deleteById(id);
        } else {
            throw new RuntimeException("Profile not found with id: " + id);
        }
    }
}
