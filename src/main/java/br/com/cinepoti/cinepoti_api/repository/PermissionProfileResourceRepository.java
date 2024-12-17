package br.com.cinepoti.cinepoti_api.repository;

import br.com.cinepoti.cinepoti_api.model.permissionProfileResource.PermissionProfileResource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionProfileResourceRepository extends JpaRepository<PermissionProfileResource, Long> {
}
