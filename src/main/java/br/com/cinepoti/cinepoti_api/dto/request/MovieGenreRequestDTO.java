package br.com.cinepoti.cinepoti_api.dto.request;


import jakarta.validation.constraints.NotNull;

public record MovieGenreRequestDTO(
        @NotNull(message = "Movie ID is required")
        Long movieId,

        @NotNull(message = "Genre ID is required")
        Long genreId
) {}
