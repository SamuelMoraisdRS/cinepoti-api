package br.com.cinepoti.cinepoti_api.service;

import br.com.cinepoti.cinepoti_api.mapper.UserMapper;
import br.com.cinepoti.cinepoti_api.model.user.User;
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

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

    }


    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        // Verifica se o e-mail ou o nome de usuário já estão em uso
        if (userRepository.findByEmail(userRequestDTO.email()).isPresent()) {
            throw new IllegalArgumentException("Email already in use");
        }

        if (userRepository.findByLogin(userRequestDTO.login()).isPresent()) {
            throw new IllegalArgumentException("login already in use");
        }
        User user = UserMapper.toEntity(userRequestDTO);
        user.setPassword(passwordEncoder.encode(userRequestDTO.password()));
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

    // Atualiza um usuário existente
    public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setLogin(userRequestDTO.login());
                    user.setEmail(userRequestDTO.email());
                    user.setPassword(passwordEncoder.encode(userRequestDTO.password()));
                    user.setGender(userRequestDTO.gender());
                    user.setBirthDate(userRequestDTO.birthDate());
                    user.setTelephone(userRequestDTO.telephone());
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
