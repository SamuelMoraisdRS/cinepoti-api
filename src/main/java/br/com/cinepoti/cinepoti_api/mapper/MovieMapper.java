package br.com.cinepoti.cinepoti_api.mapper;


import br.com.cinepoti.cinepoti_api.dto.request.MovieRequestDTO;
import br.com.cinepoti.cinepoti_api.dto.response.MovieResponseDTO;
import br.com.cinepoti.cinepoti_api.model.Genre;
import br.com.cinepoti.cinepoti_api.model.Movie;

import java.util.List;

public class MovieMapper {

    // Converts MovieRequestDTO to Movie entity
    public static Movie toEntity(MovieRequestDTO movieRequestDTO, List<Genre> genres) {
        if (movieRequestDTO == null) {
            return null;
        }

        // Create a Movie entity from the DTO
        return new Movie(
                null, // ID will be automatically generated in the database
                genres,
                movieRequestDTO.synopsis(),
                movieRequestDTO.rating(),
                movieRequestDTO.releaseDate(),
                movieRequestDTO.duration(),
                movieRequestDTO.title()
        );
    }

    // Converts Movie entity to MovieResponseDTO
    public static MovieResponseDTO toResponseDTO(Movie movie, List<Genre> genres) {
        if (movie == null) {
            return null;
        }
        List<String> genreNames = genres.stream()
                .map(Genre::getName)
                .toList();

        // Create the MovieResponseDTO using data from the Movie entity
        return new MovieResponseDTO(
                movie.getId(),
                movie.getTitle(),
                genreNames,
                movie.getDuration(),
                movie.getReleaseDate(),
                movie.getRating(),
                movie.getSynopsis()
        );
    }
}
