package br.com.cinepoti.cinepoti_api.mapper;

import br.com.cinepoti.cinepoti_api.model.resource.Resource;
import br.com.cinepoti.cinepoti_api.dto.request.ResourceRequestDTO;
import br.com.cinepoti.cinepoti_api.dto.response.ResourceResponseDTO;

public class ResourceMapper {

    // Converte o ResourceRequestDTO para a entidade Resource
    public static Resource toEntity(ResourceRequestDTO resourceRequestDTO) {
        if (resourceRequestDTO == null) {
            return null;
        }
        return new Resource(null, resourceRequestDTO.key(), resourceRequestDTO.name());
    }

    // Converte a entidade Resource para ResourceResponseDTO
    public static ResourceResponseDTO toResponseDTO(Resource resource) {
        if (resource == null) {
            return null;
        }
        // Criando o ResourceResponseDTO com os dados da entidade
        return new ResourceResponseDTO(resource.getId(), resource.getName(), resource.getKey());
    }
}
