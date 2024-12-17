package br.com.cinepoti.cinepoti_api.mapper;

import br.com.cinepoti.cinepoti_api.model.user.User;
import br.com.cinepoti.cinepoti_api.dto.request.UserRequestDTO;
import br.com.cinepoti.cinepoti_api.dto.response.UserResponseDTO;

public class UserMapper {

    // Converte o UserRequestDTO para a entidade User
    public static User toEntity(UserRequestDTO userRequestDTO) {
        if (userRequestDTO == null) {
            return null;
        }

        // Criando a entidade User a partir do DTO
        return new User(
                null, // ID será gerado automaticamente no banco
                userRequestDTO.birthDate(),
                userRequestDTO.gender(),
                userRequestDTO.cpf(),
                userRequestDTO.telephone(),
                userRequestDTO.name(),
                userRequestDTO.email(),
                userRequestDTO.password(),
                userRequestDTO.login()
        );
    }

    // Converte a entidade User para UserResponseDTO
    public static UserResponseDTO toResponseDTO(User user) {
        if (user == null) {
            return null;
        }

        // Criando o UserResponseDTO com os dados da entidade User
        return new UserResponseDTO(
                user.getId(),
                user.getLogin(),
                user.getEmail(),
                user.getName(),
                user.getCpf(),
                user.getTelephone(),
                user.getGender(),
                user.getBirthDate()
        );
    }
}
