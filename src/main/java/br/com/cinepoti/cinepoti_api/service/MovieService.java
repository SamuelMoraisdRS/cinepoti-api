package br.com.cinepoti.cinepoti_api.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import br.com.cinepoti.cinepoti_api.model.Genre;
import br.com.cinepoti.cinepoti_api.repository.GenreRepository;
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
  private final GenreRepository genreRepository; // Adicionado para buscar os gêneros

  @Autowired
  public MovieService(MovieRepository movieRepository, ExhibitionRepository exhibitionRepository,
      UserGenrePreferenceRepository userGenrePreferenceRepository, GenreRepository genreRepository) {
    this.movieRepository = movieRepository;
    this.exhibitionRepository = exhibitionRepository;
    this.userGenrePreferenceRepository = userGenrePreferenceRepository;
    this.genreRepository = genreRepository;
  }

  public MovieResponseDTO createMovie(MovieRequestDTO movieRequest) {
    List<Genre> genres = genreRepository.findAllById(movieRequest.genres()); // Ajustado para chamar o getter de genres

    Movie movie = MovieMapper.toEntity(movieRequest, genres);

    movie = this.movieRepository.save(movie);

    return MovieMapper.toResponseDTO(movie, genres); // Passa também os gêneros para o DTO
  }
  public List<MovieResponseDTO> getAllMovies() {
    // Busca todos os filmes
    List<Movie> movies = this.movieRepository.findAll();

    // Mapeia cada filme para MovieResponseDTO, incluindo os gêneros
    return movies.stream()
            .map(movie -> MovieMapper.toResponseDTO(movie, movie.getGenres())) // Passa os gêneros
            .collect(Collectors.toList());
  }


  public MovieResponseDTO getMovieById(Long id) {
    return this.movieRepository.findById(id)
            .map(movie -> MovieMapper.toResponseDTO(movie, movie.getGenres())) // Passa os gêneros
            .orElse(null);
  }


  public List<MovieResponseDTO> getMovieSuggestionsByUserId(Long userId) {
    List<Long> genreIds = this.userGenrePreferenceRepository.findByUserId(userId)
        .stream()
        .map(UserGenrePreference::getGenreId)
        .collect(Collectors.toList());

    if (genreIds.isEmpty()) {
      return Collections.emptyList();
    }

    List<MovieResponseDTO> movies = this.movieRepository.findByGenreIds(genreIds)
        .stream().distinct()
        .map(movie -> MovieMapper.toResponseDTO(movie, movie.getGenres()))
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

              // Atualiza os gêneros, caso necessário
              if (movieRequestDTO.genres() != null) {
                List<Genre> genres = genreRepository.findAllById(movieRequestDTO.genres());
                movie.setGenres(genres); // Associa os novos gêneros
              }

              // Salva o filme atualizado
              Movie updatedMovie = this.movieRepository.save(movie);

              // Mapeia para MovieResponseDTO incluindo os gêneros
              return MovieMapper.toResponseDTO(updatedMovie, movie.getGenres()); // Passa os gêneros atualizados
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
