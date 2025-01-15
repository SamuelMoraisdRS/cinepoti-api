package br.com.cinepoti.cinepoti_api.service;


import br.com.cinepoti.cinepoti_api.dto.request.ProfileRequestDTO;
import br.com.cinepoti.cinepoti_api.dto.response.ProfileResponseDTO;
import br.com.cinepoti.cinepoti_api.enums.Gender;
import br.com.cinepoti.cinepoti_api.exception.ResourceNotFoundException;
import br.com.cinepoti.cinepoti_api.exception.UnauthorizedException;
import br.com.cinepoti.cinepoti_api.exception.UsernameAlreadyInUseException;
import br.com.cinepoti.cinepoti_api.mapper.ProfileMapper;
import br.com.cinepoti.cinepoti_api.model.Profile;
import br.com.cinepoti.cinepoti_api.model.User;
import br.com.cinepoti.cinepoti_api.repository.ProfileRepository;
import br.com.cinepoti.cinepoti_api.repository.UserRepository;
import br.com.cinepoti.cinepoti_api.security.jwt.SecurityUtils;
import br.com.cinepoti.cinepoti_api.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    @Autowired
    public ProfileService(ProfileRepository profileRepository, UserRepository userRepository){
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
    }



    // Criar novo perfil
    public ProfileResponseDTO createProfile(ProfileRequestDTO dto) {
        Profile profile = ProfileMapper.toEntity(dto, null);
        profile = profileRepository.save(profile);
        return ProfileMapper.toResponseDTO(profile, null);
    }

    // Obter todos os perfis
    public List<ProfileResponseDTO> getAllProfiles() {

        List<Profile> profiles = profileRepository.findAll();
        return profiles.stream()
                .map(profile -> ProfileMapper.toResponseDTO(profile, profile.getUser()))
                .collect(Collectors.toList());
    }

    // Obter perfil por ID com dados de User
    public ProfileResponseDTO getProfileById(Long id) {
        Optional<Profile> optionalProfile = profileRepository.findById(id);

        if (optionalProfile.isEmpty()) {
            return null;
        }

        Profile profile = optionalProfile.get();
        User user = profile.getUser();

        return ProfileMapper.toResponseDTO(profile, user);
    }


    public ProfileResponseDTO updateProfile(Long id, ProfileRequestDTO dto) {
        // Verifica se o perfil existe
        Optional<Profile> optionalProfile = profileRepository.findById(id);


        if (optionalProfile.isEmpty()) {
            throw new ResourceNotFoundException("Profile not found with ID: " + id);
        }

        Profile profile = optionalProfile.get();

        // Obtém o usuário logado
        UserDetailsImpl loggedInUser = SecurityUtils.getCurrentUser();
        String loggedInUsername = loggedInUser.getUsername();

        // Verifica se o perfil pertence ao usuário logado
        if (!profile.getUser().getUsername().equals(loggedInUsername)) {
            throw new UnauthorizedException("You are not authorized to update this profile.");
        }

        User user = profile.getUser();

        System.out.println("Nome atual do perfil: " + profile.getName());

        if (dto.username() != null && !dto.username().isBlank()) {
            // Verifica se o username já está em uso
            Optional<User> optionalUser = userRepository.findByUsername(dto.username());
            if (optionalUser.isPresent() && !optionalUser.get().equals(profile.getUser())) {
                throw new UsernameAlreadyInUseException("Username '" + dto.username() + "' is already in use.");
            }

            if (user != null) {
                user.setUsername(dto.username());
                userRepository.save(user);
            } else {
                throw new UsernameAlreadyInUseException("No user associated with profile ID: " + id);
            }
        }
        profile.setName(dto.name());
        profile.setBirthdate(dto.birthdate());
        profile.setGender(dto.gender() == null ? null :
                Converter.stringToEnum(Gender.class, dto.gender().toString()));
        profile.setCpf(dto.cpf());
        profile.setPhone(dto.phone());

        profile = profileRepository.save(profile);

        return ProfileMapper.toResponseDTO(profile, profile.getUser());
    }



    // Excluir perfil por ID
    public boolean deleteProfile(Long id) {
        if (profileRepository.existsById(id)) {
            profileRepository.deleteById(id);
            return true; // Deletado com sucesso
        }
        return false; // Não encontrado
    }
}