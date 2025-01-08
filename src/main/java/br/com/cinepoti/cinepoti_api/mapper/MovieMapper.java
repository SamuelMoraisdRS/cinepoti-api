package br.com.cinepoti.cinepoti_api.mapper;


import br.com.cinepoti.cinepoti_api.dto.request.MovieRequestDTO;
import br.com.cinepoti.cinepoti_api.dto.response.MovieResponseDTO;
import br.com.cinepoti.cinepoti_api.model.Movie;

public class MovieMapper {

    // Converts MovieRequestDTO to Movie entity
    public static Movie toEntity(MovieRequestDTO movieRequestDTO) {
        if (movieRequestDTO == null) {
            return null;
        }

        // Create a Movie entity from the DTO
        return new Movie(
                null, // ID will be automatically generated in the database
                movieRequestDTO.title(),
                movieRequestDTO.duration(),
                movieRequestDTO.releaseDate(),
                movieRequestDTO.rating(),
                movieRequestDTO.synopsis()
        );
    }

    // Converts Movie entity to MovieResponseDTO
    public static MovieResponseDTO toResponseDTO(Movie movie) {
        if (movie == null) {
            return null;
        }

        // Create the MovieResponseDTO using data from the Movie entity
        return new MovieResponseDTO(
                movie.getId(),
                movie.getTitle(),
                movie.getDuration(),
                movie.getReleaseDate(),
                movie.getRating(),
                movie.getSynopsis()
        );
    }
}
