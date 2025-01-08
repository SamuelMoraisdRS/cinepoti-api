package br.com.cinepoti.cinepoti_api.dto.response;

public record MovieGenreResponseDTO(
        Long id,
        Long movieId,
        String movieTitle, // Optionally include movie title
        Long genreId,
        String genreName // Optionally include genre name
) {}
