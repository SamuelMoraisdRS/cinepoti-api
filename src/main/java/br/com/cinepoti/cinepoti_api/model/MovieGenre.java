package br.com.cinepoti.cinepoti_api.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "CP_MOVIE_GENRE")
public class MovieGenre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false) // chave estrangeira para Movie
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "genre_id", nullable = false) // chave estrangeira para Genre
    private Genre genre;

    public MovieGenre() {
    }

    public MovieGenre(Long id, Genre genre, Movie movie) {
        this.id = id;
        this.genre = genre;
        this.movie = movie;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieGenre that = (MovieGenre) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
