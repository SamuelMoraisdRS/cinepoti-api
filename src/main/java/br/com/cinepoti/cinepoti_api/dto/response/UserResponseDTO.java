package br.com.cinepoti.cinepoti_api.dto.response;


import br.com.cinepoti.cinepoti_api.enums.Gender;

import java.time.LocalDate;

public record UserResponseDTO(
        Long id,
        String login,
        String email,
        String cpf,
        String name,
        String telephone,
        Gender gender,
        LocalDate birthDate
) {}
