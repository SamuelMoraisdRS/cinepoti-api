package br.com.cinepoti.cinepoti_api.dto.response;

import br.com.cinepoti.cinepoti_api.model.Address;

public record CinemaRoomResponseDTO(
        Long id,
        String name,
        Address address
) {}
