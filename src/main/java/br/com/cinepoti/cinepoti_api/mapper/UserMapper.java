package br.com.cinepoti.cinepoti_api.mapper;

import br.com.cinepoti.cinepoti_api.enums.UserType;
import br.com.cinepoti.cinepoti_api.model.User;
import br.com.cinepoti.cinepoti_api.dto.request.UserRequestDTO;
import br.com.cinepoti.cinepoti_api.dto.response.UserResponseDTO;
import br.com.cinepoti.cinepoti_api.util.Converter;

public class UserMapper {

    // Converts UserRequestDTO to User entity
    public static User toEntity(UserRequestDTO userRequestDTO) {
        if (userRequestDTO == null) {
            return null;
        }

        // Create a User entity from the UserRequestDTO

        UserType userType = userRequestDTO.userType() == null ? UserType.COMMON :
                Converter.stringToEnum(UserType.class, userRequestDTO.userType());

        return new User(
                null, // ID will be automatically generated in the database
                userType,
                userRequestDTO.birthdate(),
                userRequestDTO.gender(),
                userRequestDTO.phone(),
                userRequestDTO.cpf(),
                userRequestDTO.name(),
                userRequestDTO.email(),
                userRequestDTO.passwordHash(),
                userRequestDTO.username() // Reference to 'username'

        );
    }

    // Converts User entity to UserResponseDTO
    public static UserResponseDTO toResponseDTO(User user) {
        if (user == null) {
            return null;
        }

        // Create the UserResponseDTO using data from the User entity
        return new UserResponseDTO(
                user.getId(),
                user.getUserType().toString(),
                user.getUsername(), // Reference to 'username'
                user.getEmail(),
                user.getName(),
                user.getCpf(),
                user.getPhone(),
                user.getGender(),
                user.getBirthdate()
        );
    }
}
