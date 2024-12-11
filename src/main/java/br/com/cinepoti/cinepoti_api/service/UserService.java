package br.com.cinepoti.cinepoti_api.service;

import br.com.cinepoti.cinepoti_api.model.user.User;
import br.com.cinepoti.cinepoti_api.model.user.UserRequestDTO;
import br.com.cinepoti.cinepoti_api.model.user.UserResponseDTO;
import br.com.cinepoti.cinepoti_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserResponseDTO saveUser(UserRequestDTO userRequestDTO) {
        // Cria um novo usuário a partir do DTO
        User user = new User(null, userRequestDTO.userName(), userRequestDTO.email(), userRequestDTO.password());
        user = userRepository.save(user);

        // Retorna a resposta DTO
        return new UserResponseDTO(user.getId(), user.getUserName(), user.getEmail());
    }

    public List<UserResponseDTO> getAllUsers() {
        // Recupera todos os usuários do repositório
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> new UserResponseDTO(user.getId(), user.getUserName(), user.getEmail()))
                .collect(Collectors.toList());
    }

    public UserResponseDTO getUserById(UUID id) {
        // Busca o usuário pelo ID
        return userRepository.findById(id)
                .map(user -> new UserResponseDTO(user.getId(), user.getUserName(), user.getEmail()))
                .orElse(null); // Retorna null se não encontrar
    }

    public UserResponseDTO updateUser(UUID id, UserRequestDTO userRequestDTO) {
        // Atualiza o usuário com o ID
        return userRepository.findById(id)
                .map(user -> {
                    user.setUserName(userRequestDTO.userName());
                    user.setEmail(userRequestDTO.email());
                    user.setPassword(userRequestDTO.password());
                    user = userRepository.save(user);
                    return new UserResponseDTO(user.getId(), user.getUserName(), user.getEmail());
                })
                .orElse(null); // Retorna null se não encontrar
    }

}
