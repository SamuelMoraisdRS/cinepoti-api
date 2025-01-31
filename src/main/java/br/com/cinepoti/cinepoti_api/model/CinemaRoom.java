package br.com.cinepoti.cinepoti_api.model;


import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import br.com.cinepoti.cinepoti_api.dto.request.SeatRequestDTO;
import br.com.cinepoti.cinepoti_api.mapper.SeatMapper;

@Entity
@Table(name = "CP_CINEMA_ROOM")
public class CinemaRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @OneToMany(mappedBy = "cinemaRoom", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Seat> seats;

    public CinemaRoom() {
    }

    public CinemaRoom(Long id) {
        this.id = id;
    }

    public CinemaRoom(Long id, Address address, String name) {
        this.id = id;
        this.address = address;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSeats(List<Seat> seats){
        this.seats = seats;
    }

    public void setSeatsWithDTO(List<SeatRequestDTO> seats){
        this.seats = seats.stream()
        .map(seat -> SeatMapper.toEntity(seat, null))
        .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CinemaRoom that = (CinemaRoom) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
