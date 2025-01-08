package br.com.cinepoti.cinepoti_api.dto.response;

import java.time.LocalDate;

public record MovieResponseDTO(
        Long id,
        String title,
        Integer duration,
        LocalDate releaseDate,
        Double rating,
        String synopsis
) {}
