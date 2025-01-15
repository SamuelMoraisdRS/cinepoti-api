package br.com.cinepoti.cinepoti_api.model;


import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "CP_SEAT")

public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "seat_row", nullable = false)
    private String row;

    @Column(name = "seat_number", nullable = false)
    private String number;

    @ManyToOne
    @JoinColumn(name = "cinemaRoom_id", nullable = false)
    private CinemaRoom cinemaRoom;

    @Column(nullable = false)
    private Double price;

    public Seat() {
    }

    public Seat(Long id){
        this.id = id;
    }

    public Seat(Long id, Double price, CinemaRoom cinemaRoom, String number, String row) {
        this.id = id;
        this.price = price;
        this.cinemaRoom = cinemaRoom;
        this.number = number;
        this.row = row;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public CinemaRoom getCinemaRoom() {
        return cinemaRoom;
    }

    public void setCinemaRoom(CinemaRoom cinemaRoom) {
        this.cinemaRoom = cinemaRoom;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return Objects.equals(id, seat.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
