package br.com.cinepoti.cinepoti_api.mapper;

import br.com.cinepoti.cinepoti_api.model.permissionProfileResource.PermissionProfileResource;
import br.com.cinepoti.cinepoti_api.dto.request.PermissionProfileResourceRequestDTO;
import br.com.cinepoti.cinepoti_api.dto.response.PermissionProfileResourceResponseDTO;
import br.com.cinepoti.cinepoti_api.model.profile.Profile;
import br.com.cinepoti.cinepoti_api.dto.response.ProfileResponseDTO;
import br.com.cinepoti.cinepoti_api.model.resource.Resource;
import br.com.cinepoti.cinepoti_api.dto.response.ResourceResponseDTO;

public class PermissionProfileResourceMapper {

    public static PermissionProfileResource toEntity(PermissionProfileResourceRequestDTO dto) {

        if (dto == null) {
            return null;
        }

        Profile profile = ProfileMapper.toEntity(dto.profileRequestDTO());
        Resource resource = ResourceMapper.toEntity(dto.resourceRequestDTO());

        return new PermissionProfileResource(null, profile, resource);
    }

    public static PermissionProfileResourceResponseDTO toResponseDTO(PermissionProfileResource entity) {

        if (entity == null) {
            return null;
        }

        ResourceResponseDTO resourceResponseDTO = ResourceMapper.toResponseDTO(entity.getResource());
        ProfileResponseDTO profileResponseDTO = ProfileMapper.toResponseDTO(entity.getProfile());

        return new PermissionProfileResourceResponseDTO(entity.getId(), profileResponseDTO, resourceResponseDTO);
    }
}
