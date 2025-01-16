package br.com.cinepoti.cinepoti_api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.cinepoti.cinepoti_api.dto.request.MovieRequestDTO;
import br.com.cinepoti.cinepoti_api.dto.response.MovieResponseDTO;
import br.com.cinepoti.cinepoti_api.mapper.MovieMapper;
import br.com.cinepoti.cinepoti_api.model.Movie;
import br.com.cinepoti.cinepoti_api.model.UserGenrePreference;
import br.com.cinepoti.cinepoti_api.repository.ExhibitionRepository;
import br.com.cinepoti.cinepoti_api.repository.MovieRepository;
import br.com.cinepoti.cinepoti_api.repository.UserGenrePreferenceRepository;

import org.springframework.stereotype.Service;

@Service
public class MovieService {
  private final MovieRepository movieRepository;
  private final ExhibitionRepository exhibitionRepository;
  private final UserGenrePreferenceRepository userGenrePreferenceRepository;

  @Autowired
  public MovieService(MovieRepository movieRepository, ExhibitionRepository exhibitionRepository,
      UserGenrePreferenceRepository userGenrePreferenceRepository) {
    this.movieRepository = movieRepository;
    this.exhibitionRepository = exhibitionRepository;
    this.userGenrePreferenceRepository = userGenrePreferenceRepository;
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

  public List<MovieResponseDTO> getMovieSuggestionsByUserId(Long userId) {
    List<Long> genreIds = this.userGenrePreferenceRepository.findByUserId(userId)
        .stream()
        .map(UserGenrePreference::getGenreId)
        .collect(Collectors.toList());

    List<MovieResponseDTO> movies = this.movieRepository.findByGenreIds(genreIds)
        .stream()
        .map(MovieMapper::toResponseDTO)
        .collect(Collectors.toList());
        
    return movies;
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
