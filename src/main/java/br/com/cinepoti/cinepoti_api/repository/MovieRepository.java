package br.com.cinepoti.cinepoti_api.repository;

import br.com.cinepoti.cinepoti_api.model.Movie;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MovieRepository extends JpaRepository<Movie, Long> {
  @Query("SELECT m FROM Movie m " +
      "JOIN m.movieGenres mg " +
      "WHERE mg.genre_id IN :genreIds")
  List<Movie> findByGenreIds(@Param("genreIds") List<Long> genreIds);
}
