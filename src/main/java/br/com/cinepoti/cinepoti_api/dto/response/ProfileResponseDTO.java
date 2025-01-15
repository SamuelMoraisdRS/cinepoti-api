package br.com.cinepoti.cinepoti_api.dto.response;


import java.time.LocalDate;

public record ProfileResponseDTO(
        Long id,
        String username,
        String email,
        String name,
        String cpf,
        String phone,
        String gender,
        LocalDate birthdate
) { }
