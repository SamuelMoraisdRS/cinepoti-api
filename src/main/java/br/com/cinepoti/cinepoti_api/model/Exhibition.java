package br.com.cinepoti.cinepoti_api.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "CP_EXHIBITION")

public class Exhibition implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "cinemaRoom_id", nullable = false)
    private CinemaRoom cinemaRoom;

    @Column(nullable = false)
    private LocalDateTime startTime;

    // Adicionado para simplificar a consulta. Reduz Joins na tabela Booking, facilitando busca por assentos livres
    @OneToMany(mappedBy = "exhibition", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ticket> tickets;

    @OneToMany(mappedBy = "exhibition", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Booking> bookings;

    public Exhibition() {
    }

    public Exhibition(Long id) {
        this.id = id;
    }

    public Exhibition(Long id, LocalDateTime startTime, CinemaRoom cinemaRoom, Movie movie) {
        this.id = id;
        this.startTime = startTime;
        this.cinemaRoom = cinemaRoom;
        this.movie = movie;
    }

    public Exhibition(Long id, LocalDateTime startTime, Long cinemaRoomId, Long movieId) {
        this.id = id;
        this.startTime = startTime;
        this.cinemaRoom = new CinemaRoom(cinemaRoomId);
        this.movie = new Movie(movieId);
    }

    public boolean hasTickets() {
        return !tickets.isEmpty();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public CinemaRoom getCinemaRoom() {
        return cinemaRoom;
    }

    public void setCinemaRoom(CinemaRoom cinemaRoom) {
        this.cinemaRoom = cinemaRoom;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public List<Long> getAssociatedBookings() {
        return bookings.stream().map(Booking::getId).toList();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exhibition that = (Exhibition) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
