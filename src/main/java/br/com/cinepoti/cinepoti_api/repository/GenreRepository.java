package br.com.cinepoti.cinepoti_api.repository;

import br.com.cinepoti.cinepoti_api.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
