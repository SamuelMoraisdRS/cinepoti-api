package br.com.cinepoti.cinepoti_api.model;


import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "CP_BOOK_TICKET")
public class BookTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "seat_id", nullable = false)
    private Seat seat;

    @ManyToOne
    @JoinColumn(name = "exhibition_id", nullable = false)
    private Exhibition exhibition;

    public BookTicket() {
    }

    public BookTicket(Long id, Seat seat, Booking booking, Exhibition exhibition) {
        this.id = id;
        this.seat = seat;
        this.booking = booking;
        this.exhibition = exhibition;
    }

    public BookTicket(Long id, Long seatId, Long bookingId, Long exhibitionId) {
        this.id = id;
        this.seat = new Seat(seatId);
        this.booking = new Booking(bookingId);
        this.exhibition = new Exhibition(exhibitionId);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookTicket that = (BookTicket) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
