package br.com.cinepoti.cinepoti_api.service;

import br.com.cinepoti.cinepoti_api.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Implementation of the Spring Security UserDetails interface, which is used to store user-specific data.
 * This class provides the necessary details about the user to Spring Security for authentication and authorization.
 */
public class UserDetailsImpl implements UserDetails {

    private Long id;

    private final String name;

    private final String userName;

    private final String email;

    private final String password;

    public UserDetailsImpl(Long id, String name, String userName, String email, String password,
                           Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    /**
     * Creates a new UserDetailsImpl instance from a User entity.
     *
     * @param user The User entity to convert into UserDetails.
     * @return A new instance of UserDetailsImpl.
     */
    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = List.of(
                new SimpleGrantedAuthority(user.getUserType().name())
        );

        return new UserDetailsImpl(user.getId(), user.getName(), user.getUsername(), user.getEmail(), user.getPasswordHash(), authorities);
    }

    private final Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
