package br.com.cinepoti.cinepoti_api.dto.response;

public record AddressResponseDTO(
        Long id,
        String street,
        String city,
        String state,
        String neighborhood,
        String streetNumber,
        String cep
) {}
