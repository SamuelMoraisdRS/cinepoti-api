package br.com.cinepoti.cinepoti_api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.cinepoti.cinepoti_api.dto.request.MovieRequestDTO;
import br.com.cinepoti.cinepoti_api.dto.response.MovieResponseDTO;
import br.com.cinepoti.cinepoti_api.mapper.MovieMapper;
import br.com.cinepoti.cinepoti_api.model.Movie;
import br.com.cinepoti.cinepoti_api.repository.ExhibitionRepository;
import br.com.cinepoti.cinepoti_api.repository.MovieRepository;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
  private final MovieRepository movieRepository;
  private final ExhibitionRepository exhibitionRepository;

  @Autowired
  public MovieService(MovieRepository movieRepository, ExhibitionRepository exhibitionRepository) {
    this.movieRepository = movieRepository;
    this.exhibitionRepository = exhibitionRepository;
  }

  public MovieResponseDTO createMovie(MovieRequestDTO movieRequest) {
    Movie movie = MovieMapper.toEntity(movieRequest);
    movie = this.movieRepository.save(movie);

    return MovieMapper.toResponseDTO(movie);
  }

  public List<MovieResponseDTO> getAllMovies() {
    List<Movie> movies = this.movieRepository.findAll();

    return movies.stream()
        .map(MovieMapper::toResponseDTO)
        .collect(Collectors.toList());
  }

  public MovieResponseDTO getMovieById(Long id) {
    return this.movieRepository.findById(id)
        .map(MovieMapper::toResponseDTO)
        .orElse(null);
  }

  public MovieResponseDTO updateMovie(Long id, MovieRequestDTO movieRequestDTO) {
    return this.movieRepository.findById(id)
        .map(movie -> {
          movie.setDuration(movieRequestDTO.duration());
          movie.setRating(movieRequestDTO.rating());
          movie.setReleaseDate(movieRequestDTO.releaseDate());
          movie.setSynopsis(movieRequestDTO.synopsis());
          movie.setTitle(movieRequestDTO.title());

          Movie updatedMovie = this.movieRepository.save(movie);
          return MovieMapper.toResponseDTO(updatedMovie);
        })
        .orElse(null);
  }

  // Deleta um usuário por ID
  public void deleteMovie(Long id) {
    // TODO: Verificar se existe alguma exibição relacionada
    if (!this.movieRepository.existsById(id)) {
      throw new RuntimeException("Movie not found with id: " + id);
    }
    this.movieRepository.deleteById(id);
  }
}
