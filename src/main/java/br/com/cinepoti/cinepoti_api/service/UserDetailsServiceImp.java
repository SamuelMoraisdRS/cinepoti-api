package br.com.cinepoti.cinepoti_api.service;

import br.com.cinepoti.cinepoti_api.model.User;
import br.com.cinepoti.cinepoti_api.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Loads user details by username.
     *
     * @param username The username to search for.
     * @return UserDetails of the user.
     * @throws UsernameNotFoundException if the user is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Verifica se o usuário existe no banco de dados, caso contrário lança exceção
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found : " + username));

        // Converte o usuário para UserDetailsImpl
        return UserDetailsImpl.build(user);
    }
}
