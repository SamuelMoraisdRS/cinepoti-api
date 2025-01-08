package br.com.cinepoti.cinepoti_api.repository;

import br.com.cinepoti.cinepoti_api.model.MovieGenre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieGenreRepository extends JpaRepository<MovieGenre, Long> {
}
