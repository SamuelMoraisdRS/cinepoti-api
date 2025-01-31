package br.com.cinepoti.cinepoti_api.controller;

import br.com.cinepoti.cinepoti_api.dto.request.ProfileRequestDTO;
import br.com.cinepoti.cinepoti_api.dto.response.ProfileResponseDTO;
import br.com.cinepoti.cinepoti_api.service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/perfil")
@CrossOrigin
public class ProfileController {

    private final ProfileService profileService;


    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    // Endpoint para criar um novo perfil
    @PostMapping("/cadastrar")
    public ResponseEntity<ProfileResponseDTO> createProfile(@RequestBody @Valid ProfileRequestDTO profileRequestDTO) {
        ProfileResponseDTO profileResponseDTO = profileService.createProfile(profileRequestDTO);
        return new ResponseEntity<>(profileResponseDTO, HttpStatus.CREATED);
    }

    // Endpoint para listar todos os perfis
    @GetMapping
    public ResponseEntity<List<ProfileResponseDTO>> getAllProfiles() {
        List<ProfileResponseDTO> profiles = profileService.getAllProfiles();
        return new ResponseEntity<>(profiles, HttpStatus.OK);
    }

    // Endpoint para buscar um perfil por ID
    @GetMapping("/{id}")
    public ResponseEntity<ProfileResponseDTO> getProfileById(@PathVariable("id") Long id) {
        ProfileResponseDTO profileResponseDTO = profileService.getProfileById(id);

        if (profileResponseDTO != null) {
            return new ResponseEntity<>(profileResponseDTO, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Endpoint para atualizar um perfil existente
    @PutMapping("/{id}")
    public ResponseEntity<ProfileResponseDTO> updateProfile(@PathVariable("id") Long id, @RequestBody  @Valid ProfileRequestDTO profileRequestDTO) {
        ProfileResponseDTO updatedProfile = profileService.updateProfile(id, profileRequestDTO);

        if (updatedProfile != null) {
            return new ResponseEntity<>(updatedProfile, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Endpoint para excluir um perfil
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable("id") Long id) {
        boolean isDeleted = profileService.deleteProfile(id);

        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}