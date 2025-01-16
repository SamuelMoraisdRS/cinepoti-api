package br.com.cinepoti.cinepoti_api.dto.response;



import java.time.LocalDate;
import java.util.List;

public record MovieResponseDTO(
        Long id,
        String title,
        List<String> genres,
        Integer duration,
        LocalDate releaseDate,
        Double rating,
        String synopsis
) {}
