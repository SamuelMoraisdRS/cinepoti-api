package br.com.cinepoti.cinepoti_api.controller;

import br.com.cinepoti.cinepoti_api.dto.request.ProfileUserRequestDTO;
import br.com.cinepoti.cinepoti_api.dto.response.ProfileUserResponseDTO;
import br.com.cinepoti.cinepoti_api.service.ProfileUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/profiles-usuarios")
@CrossOrigin
public class ProfileUserController {

    private final ProfileUserService profileUserService;

    @Autowired
    public ProfileUserController(ProfileUserService profileUserService) {
        this.profileUserService = profileUserService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<ProfileUserResponseDTO> createProfile(@Valid @RequestBody ProfileUserRequestDTO profileUserRequestDTO) {
        ProfileUserResponseDTO profileUserResponseDTO = profileUserService.addProfileUser(profileUserRequestDTO);
        return ResponseEntity.ok(profileUserResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<ProfileUserResponseDTO>> getAllProfileUsers() {
        List<ProfileUserResponseDTO> profilesUsers = profileUserService.getAllProfileUsers();
        return ResponseEntity.ok(profilesUsers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileUserResponseDTO> getProfileUserById(@PathVariable Long id) {
        ProfileUserResponseDTO profileUserResponseDTO = profileUserService.getProfileUserById(id);
        return ResponseEntity.ok(profileUserResponseDTO);
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<ProfileUserResponseDTO> updateProfileUser(@PathVariable Long id, @Valid @RequestBody ProfileUserRequestDTO profileUserRequestDTO) {
        ProfileUserResponseDTO profileUserResponseDTO = profileUserService.updateProfileUser(id, profileUserRequestDTO);
        return ResponseEntity.ok(profileUserResponseDTO);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long id) {
        profileUserService.deleteProfileUserById(id);
        return ResponseEntity.noContent().build();
    }
}
