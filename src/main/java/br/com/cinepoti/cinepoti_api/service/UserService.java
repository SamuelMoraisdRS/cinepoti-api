package br.com.cinepoti.cinepoti_api.service;

import br.com.cinepoti.cinepoti_api.mapper.UserMapper;
import br.com.cinepoti.cinepoti_api.model.Genre;
import br.com.cinepoti.cinepoti_api.model.Profile;
import br.com.cinepoti.cinepoti_api.model.User;
import br.com.cinepoti.cinepoti_api.model.UserGenrePreference;
import br.com.cinepoti.cinepoti_api.dto.request.UserRequestDTO;
import br.com.cinepoti.cinepoti_api.dto.response.UserResponseDTO;
import br.com.cinepoti.cinepoti_api.repository.GenreRepository;
import br.com.cinepoti.cinepoti_api.repository.ProfileRepository;
import br.com.cinepoti.cinepoti_api.repository.UserGenrePreferenceRepository;
import br.com.cinepoti.cinepoti_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;
    private final GenreRepository genreRepository;
    private final UserGenrePreferenceRepository userGenrePreferenceRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,
            ProfileRepository profileRepository, GenreRepository genreRepository,
            UserGenrePreferenceRepository userGenrePreferenceRepository) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.profileRepository = profileRepository;
        this.genreRepository = genreRepository;
        this.userGenrePreferenceRepository = userGenrePreferenceRepository;
    }

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        // Verifica se o e-mail ou o nome de usuário já estão em uso
        if (userRepository.findByEmail(userRequestDTO.email()).isPresent()) {
            throw new IllegalArgumentException("Email already in use");
        }

        if (userRepository.findByUsername(userRequestDTO.username()).isPresent()) {
            throw new IllegalArgumentException("Login already in use");
        }

        Profile profile = new Profile();

        User user = UserMapper.toEntity(userRequestDTO, profile);
        user.setPasswordHash(passwordEncoder.encode(userRequestDTO.passwordHash()));

        profile.setUser(user);

        user = userRepository.save(user);

        profileRepository.save(profile);

        return UserMapper.toResponseDTO(user);
    }

    public void saveUserGenrePreferences(Long userId, List<Long> genreIds) {
        // Validar se o usuário existe
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        // Buscar os gêneros correspondentes
        List<Genre> genres = genreRepository.findAllById(genreIds);

        // Criar as preferências
        List<UserGenrePreference> preferences = genres.stream()
                .map(genre -> {
                    UserGenrePreference preference = new UserGenrePreference();
                    preference.setUser(user);
                    preference.setGenre(genre);
                    return preference;
                })
                .collect(Collectors.toList());

        // Salvar todas as preferências de uma vez
        userGenrePreferenceRepository.saveAll(preferences);
    }

    // Recupera todos os usuários
    public List<UserResponseDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // Recupera um usuário pelo ID
    public UserResponseDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(UserMapper::toResponseDTO)
                .orElse(null);
    }

    /*
     * Métodos com "object" retornam instancias das entidades, ao invés de DTOs. Fiz eles pra serem usados
     * dentro da camada Service, com os que retornam DTOs sendo reservados p/ comunicação cm os controllers.
     * Um alternativa a isso seria sobrecarregar o metodo toEntity dos mappers para gerar instancias a partir
     * dos ResponsesDTOs, mas desta forma poupamos uma chamada de função.
     */
    public User getUserObjectById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    // Atualiza um usuário existente
    public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO) {
        return userRepository.findById(id)
                .map(user -> {
                    user = UserMapper.toEntity(userRequestDTO, null);
                    user = userRepository.save(user);

                    User updatedUser = this.userRepository.save(user);
                    return UserMapper.toResponseDTO(updatedUser);
                })
                .orElse(null);
    }

    // Deleta um usuário por ID
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }
}
