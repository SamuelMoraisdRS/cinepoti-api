package br.com.cinepoti.cinepoti_api.service;

import br.com.cinepoti.cinepoti_api.mapper.UserMapper;
import br.com.cinepoti.cinepoti_api.model.User;
import br.com.cinepoti.cinepoti_api.dto.request.UserRequestDTO;
import br.com.cinepoti.cinepoti_api.dto.response.UserResponseDTO;
import br.com.cinepoti.cinepoti_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        // Verifica se o e-mail ou o nome de usuário já estão em uso
        if (userRepository.findByEmail(userRequestDTO.email()).isPresent()) {
            throw new IllegalArgumentException("Email already in use");
        }

        if (userRepository.findByUsername(userRequestDTO.username()).isPresent()) {
            throw new IllegalArgumentException("login already in use");
        }
        User user = UserMapper.toEntity(userRequestDTO);
        user.setPasswordHash(passwordEncoder.encode(userRequestDTO.passwordHash()));
        user = userRepository.save(user);

        return UserMapper.toResponseDTO(user);
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
                    user.setUsername(userRequestDTO.username());
                    user.setEmail(userRequestDTO.email());
                    user.setPasswordHash(passwordEncoder.encode(userRequestDTO.passwordHash()));
                    user.setGender(userRequestDTO.gender());
                    user.setBirthdate(userRequestDTO.birthdate());
                    user.setPhone(userRequestDTO.phone());
                    user.setCpf(userRequestDTO.cpf());
                    user = userRepository.save(user);
                    return UserMapper.toResponseDTO(user);
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
