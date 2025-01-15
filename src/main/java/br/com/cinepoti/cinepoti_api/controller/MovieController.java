package br.com.cinepoti.cinepoti_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import br.com.cinepoti.cinepoti_api.dto.request.MovieRequestDTO;
import br.com.cinepoti.cinepoti_api.dto.response.MovieResponseDTO;
import br.com.cinepoti.cinepoti_api.service.MovieService;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/filmes")
@CrossOrigin
public class MovieController {
  private final MovieService movieService;

  @Autowired
  public MovieController(MovieService movieService) {
    this.movieService = movieService;
  }

  @PreAuthorize("hasAuthority('ADMIN')")
  @PostMapping
  public ResponseEntity<MovieResponseDTO> createMovie(@Valid @RequestBody MovieRequestDTO movieRequestDTO) {
    MovieResponseDTO movieResponseDTO = this.movieService.createMovie(movieRequestDTO);

    return new ResponseEntity<>(movieResponseDTO, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<MovieResponseDTO>> getAllMovies() {
    List<MovieResponseDTO> movies = this.movieService.getAllMovies();
    return new ResponseEntity<>(movies, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<MovieResponseDTO> getMovieById(@PathVariable("id") Long id) {
    MovieResponseDTO movie = this.movieService.getMovieById(id);
    if (movie != null) {
      return new ResponseEntity<>(movie, HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @PreAuthorize("hasAuthority('ADMIN')")
  @PutMapping("/{id}")
  public ResponseEntity<MovieResponseDTO> updateMovie(
      @PathVariable("id") Long id,
      @RequestBody MovieRequestDTO userRequestDTO) {

    MovieResponseDTO userResponseDTO = this.movieService.updateMovie(id, userRequestDTO);
    if (userResponseDTO != null) {
      return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @PreAuthorize("hasAuthority('ADMIN')")
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteMovie(@PathVariable("id") Long id) {
    this.movieService.deleteMovie(id);
    return ResponseEntity.ok().build();
  }
}
