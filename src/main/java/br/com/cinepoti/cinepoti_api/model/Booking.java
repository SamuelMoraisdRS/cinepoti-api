package br.com.cinepoti.cinepoti_api.model;

import br.com.cinepoti.cinepoti_api.enums.BookingStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;

@Entity
@Table(name = "CP_BOOKING")
public class Booking implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Booking date is required")
    @Column(nullable = false)
    private LocalDate bookingDate;

    @NotNull(message = "Total amount is required")
    @Column(nullable = false)
    private Double totalAmount;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Booking status is required")
    @Column(nullable = false)
    private BookingStatus status;

    @ManyToOne
    @JoinColumn(name = "exhibition_id", nullable = false)
    private Exhibition exhibition;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
    private Map<Long, Ticket> tickets;

    @OneToOne(mappedBy = "booking")
    private Payment payment;

    public Booking() {
    }

    public Booking(Long id, User user, Exhibition exhibition, BookingStatus status, LocalDate bookingDate   , Double totalAmount) {
        this.id = id;
        this.user = user;
        this.exhibition = exhibition;
        this.status = status;
        this.bookingDate = bookingDate;
        this.totalAmount = totalAmount;
    }

    public Booking(Long id, User user, Exhibition exhibition, BookingStatus status, LocalDate bookingDate) {
        this.id = id;
        this.user = user;
        this.exhibition = exhibition;
        this.status = status;
        this.bookingDate = bookingDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Exhibition getExhibition() {
        return exhibition;
    }

    public void setExhibition(Exhibition exhibition) {
        this.exhibition = exhibition;
    }

    public Map<Long, Ticket> getTickets() {
        return this.tickets;
    }

    public void setTickets(Map<Long,Ticket> tickets) {
        this.tickets = tickets;
    }

    public Payment getPayment() {
        return this.payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Booking booking = (Booking) o;
        return Objects.equals(id, booking.id) &&
                Objects.equals(bookingDate, booking.bookingDate) &&
                Objects.equals(totalAmount, booking.totalAmount) &&
                status == booking.status &&
                Objects.equals(exhibition, booking.exhibition) &&
                Objects.equals(user, booking.user) &&
                Objects.equals(tickets, booking.tickets);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }


}
