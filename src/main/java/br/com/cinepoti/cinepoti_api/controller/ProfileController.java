package br.com.cinepoti.cinepoti_api.controller;

import br.com.cinepoti.cinepoti_api.dto.request.ProfileRequestDTO;
import br.com.cinepoti.cinepoti_api.dto.response.ProfileResponseDTO;
import br.com.cinepoti.cinepoti_api.service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/profiles")
@CrossOrigin
public class ProfileController {

    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService){
        this.profileService = profileService; // Injeta a dependência do ProfileService
    }


    @PostMapping("/cadastrar")
    public ResponseEntity<ProfileResponseDTO> createProfile(@Valid @RequestBody ProfileRequestDTO profileRequestDTO) {
        ProfileResponseDTO profileResponseDTO = profileService.createProfile(profileRequestDTO);
        return ResponseEntity.ok(profileResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<ProfileResponseDTO>> getAllProfiles() {
        List<ProfileResponseDTO> profiles = profileService.getAllProfiles();
        return ResponseEntity.ok(profiles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileResponseDTO> getProfileById(@PathVariable Long id) {
        ProfileResponseDTO profileResponseDTO = profileService.getProfileById(id);
        return ResponseEntity.ok(profileResponseDTO);
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<ProfileResponseDTO> updateProfile(@PathVariable Long id, @Valid @RequestBody ProfileRequestDTO profileRequestDTO) {
        ProfileResponseDTO profileResponseDTO = profileService.updateProfile(id, profileRequestDTO);
        return ResponseEntity.ok(profileResponseDTO);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long id) {
        profileService.deleteProfile(id);
        return ResponseEntity.noContent().build();
    }
}
