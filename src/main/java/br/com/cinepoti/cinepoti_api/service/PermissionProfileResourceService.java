package br.com.cinepoti.cinepoti_api.service;

import br.com.cinepoti.cinepoti_api.mapper.PermissionProfileResourceMapper;
import br.com.cinepoti.cinepoti_api.model.permissionProfileResource.PermissionProfileResource;
import br.com.cinepoti.cinepoti_api.dto.request.PermissionProfileResourceRequestDTO;
import br.com.cinepoti.cinepoti_api.dto.response.PermissionProfileResourceResponseDTO;
import br.com.cinepoti.cinepoti_api.repository.PermissionProfileResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PermissionProfileResourceService {

    private final PermissionProfileResourceRepository repository;

    @Autowired
    public PermissionProfileResourceService(PermissionProfileResourceRepository repository) {
        this.repository = repository;
    }

    public PermissionProfileResourceResponseDTO createPPR(PermissionProfileResourceRequestDTO dto) {
        PermissionProfileResource entity = PermissionProfileResourceMapper.toEntity(dto);

        PermissionProfileResource savedEntity = repository.save(entity);

        return PermissionProfileResourceMapper.toResponseDTO(savedEntity);
    }

    public List<PermissionProfileResourceResponseDTO> findAll() {
        // Busca todas as entidades e mapeia para DTO de resposta
        return repository.findAll().stream()
                .map(PermissionProfileResourceMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public PermissionProfileResourceResponseDTO findById(Long id) {
        Optional<PermissionProfileResource> entity = repository.findById(id);

        return entity.map(PermissionProfileResourceMapper::toResponseDTO)
                .orElseThrow(() -> new RuntimeException("PermissionProfileResource não encontrado com ID: " + id));
    }

    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("PermissionProfileResource não encontrado com ID: " + id);
        }

        repository.deleteById(id);
    }
}
