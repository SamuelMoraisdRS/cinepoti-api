package br.com.cinepoti.cinepoti_api.service;

import br.com.cinepoti.cinepoti_api.mapper.ResourceMapper;
import br.com.cinepoti.cinepoti_api.model.resource.Resource;
import br.com.cinepoti.cinepoti_api.dto.request.ResourceRequestDTO;
import br.com.cinepoti.cinepoti_api.dto.response.ResourceResponseDTO;
import br.com.cinepoti.cinepoti_api.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ResourceService {

    private final ResourceRepository resourceRepository;

    @Autowired
    public ResourceService(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    // Adiciona um novo Resource
    public ResourceResponseDTO addResource(ResourceRequestDTO resourceRequestDTO) {
        Resource resource = ResourceMapper.toEntity(resourceRequestDTO); // Usando o mapper para converter DTO para entidade
        Resource savedResource = resourceRepository.save(resource);
        return ResourceMapper.toResponseDTO(savedResource); // Usando o mapper para converter a entidade para DTO
    }

    // Recupera todos os Resources
    public List<ResourceResponseDTO> getAllResources() {
        List<Resource> resources = resourceRepository.findAll();
        return resources.stream()
                .map(ResourceMapper::toResponseDTO) // Usando o mapper para converter a entidade para DTO
                .collect(Collectors.toList());
    }

    // Recupera um Resource por ID
    public ResourceResponseDTO getResourcesById(Long id) {
        return resourceRepository.findById(id)
                .map(ResourceMapper::toResponseDTO) // Usando o mapper para converter a entidade para DTO
                .orElse(null);
    }

    // Atualiza um Resource existente
    public ResourceResponseDTO updateResource(Long id, ResourceRequestDTO resourceRequestDTO) {
        return resourceRepository.findById(id)
                .map(resource -> {
                    resource.setName(resourceRequestDTO.name());
                    resource.setKey(resourceRequestDTO.key());
                    Resource updatedResource = resourceRepository.save(resource);
                    return ResourceMapper.toResponseDTO(updatedResource); // Usando o mapper para converter a entidade para DTO
                })
                .orElse(null);
    }

    // Deleta um Resource por ID
    public void deleteById(Long id) {
        Optional<Resource> resourceOpt = resourceRepository.findById(id);
        if (resourceOpt.isPresent()) {
            resourceRepository.deleteById(id);
        } else {
            throw new RuntimeException("Resource not found with id: " + id);
        }
    }
}
