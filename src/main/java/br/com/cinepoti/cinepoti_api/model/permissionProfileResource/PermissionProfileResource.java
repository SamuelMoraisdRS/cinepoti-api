package br.com.cinepoti.cinepoti_api.model.permissionProfileResource;


import br.com.cinepoti.cinepoti_api.model.profile.Profile;
import br.com.cinepoti.cinepoti_api.model.resource.Resource;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "CP_PERMISSION_PROFILE_RESOURCE")
public class PermissionProfileResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "ID_PROFILE")
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "ID_RESOURCE")
    private Resource resource;


    public PermissionProfileResource() {
    }

    public PermissionProfileResource(Long id, Profile profile, Resource resource) {
        this.id = id;
        this.profile = profile;
        this.resource = resource;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PermissionProfileResource that = (PermissionProfileResource) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
