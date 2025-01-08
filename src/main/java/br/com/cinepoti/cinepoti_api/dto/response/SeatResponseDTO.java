package br.com.cinepoti.cinepoti_api.dto.response;

public record SeatResponseDTO(
        Long id,
        Long cinemaRoomId,
        String row,
        String number,
        Double price
) {}
