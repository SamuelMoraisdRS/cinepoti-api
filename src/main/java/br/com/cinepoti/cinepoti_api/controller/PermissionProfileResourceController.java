package br.com.cinepoti.cinepoti_api.controller;

import br.com.cinepoti.cinepoti_api.dto.request.PermissionProfileResourceRequestDTO;
import br.com.cinepoti.cinepoti_api.dto.response.PermissionProfileResourceResponseDTO;
import br.com.cinepoti.cinepoti_api.service.PermissionProfileResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/perimissao-perfil-recurso")
@CrossOrigin

public class PermissionProfileResourceController {

    private final PermissionProfileResourceService service;

    @Autowired
    public PermissionProfileResourceController(PermissionProfileResourceService service) {
        this.service = service;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<PermissionProfileResourceResponseDTO> create(
            @RequestBody PermissionProfileResourceRequestDTO requestDTO) {
        PermissionProfileResourceResponseDTO response = service.createPPR(requestDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<PermissionProfileResourceResponseDTO>> getAll() {
        List<PermissionProfileResourceResponseDTO> responseList = service.findAll();
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PermissionProfileResourceResponseDTO> getById(@PathVariable Long id) {
        PermissionProfileResourceResponseDTO response = service.findById(id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
