package br.com.cinepoti.cinepoti_api.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "CP_ADDRESS")
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Street is required")
    @Size(min = 1, max = 100, message = "Street must be between 1 and 100 characters")
    @Column(nullable = false)
    private String street;

    @NotNull(message = "City is required")
    @Size(min = 1, max = 100, message = "City must be between 1 and 100 characters")
    @Column(nullable = false)
    private String city;

    @NotNull(message = "State is required")
    @Size(min = 1, max = 100, message = "State must be between 1 and 100 characters")
    @Column(nullable = false)
    private String state;

    @NotNull(message = "Neighborhood is required")
    @Size(min = 1, max = 100, message = "Neighborhood must be between 1 and 100 characters")
    @Column(nullable = false)
    private String neighborhood;

    @NotNull(message = "Street number is required")
    @Size(min = 1, max = 10, message = "Street number must be between 1 and 10 characters")
    @Column(nullable = false)
    private String streetNumber;

    @NotNull(message = "CEP is required")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "CEP must be in the format 00000-000")
    @Column(nullable = false)
    private String cep;

    public Address() {}

    public Address(Long id, String street, String city, String state, String neighborhood, String streetNumber,
                   String cep) {
        this.id = id;
        this.street = street;
        this.city = city;
        this.state = state;
        this.neighborhood = neighborhood;
        this.streetNumber = streetNumber;
        this.cep = cep;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(id, address.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
