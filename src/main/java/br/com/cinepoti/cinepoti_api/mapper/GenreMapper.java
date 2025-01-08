package br.com.cinepoti.cinepoti_api.mapper;

import br.com.cinepoti.cinepoti_api.model.Genre;
import br.com.cinepoti.cinepoti_api.dto.request.GenreRequestDTO;
import br.com.cinepoti.cinepoti_api.dto.response.GenreResponseDTO;

public class GenreMapper {

    // Converts GenreRequestDTO to Genre entity
    public static Genre toEntity(GenreRequestDTO genreRequestDTO) {
        if (genreRequestDTO == null) {
            return null;
        }

        // Create a Genre entity from the DTO
        return new Genre(
                null, // ID will be automatically generated in the database
                genreRequestDTO.name()
        );
    }

    // Converts Genre entity to GenreResponseDTO
    public static GenreResponseDTO toResponseDTO(Genre genre) {
        if (genre == null) {
            return null;
        }

        // Create the GenreResponseDTO using data from the Genre entity
        return new GenreResponseDTO(
                genre.getId(),
                genre.getName()
        );
    }
}
