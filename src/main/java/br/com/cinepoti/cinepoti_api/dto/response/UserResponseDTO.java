package br.com.cinepoti.cinepoti_api.dto.response;

import br.com.cinepoti.cinepoti_api.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public record UserResponseDTO(
        Long id,
        String userType,
        String username,
        String email
) {}
