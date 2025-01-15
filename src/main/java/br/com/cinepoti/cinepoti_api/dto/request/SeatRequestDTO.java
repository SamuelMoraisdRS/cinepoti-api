package br.com.cinepoti.cinepoti_api.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SeatRequestDTO(
        // @NotNull(message = "Cinema Room ID is required")
        Long cinemaRoomId,

        @NotNull(message = "Row is required")
        @Size(min = 1, max = 5, message = "Row must be between 1 and 5 characters")
        String row,

        @NotNull(message = "Seat number is required")
        @Size(min = 1, max = 5, message = "Seat number must be between 1 and 5 characters")
        String number,

        @NotNull(message = "Price is required")
        Double price
) {}
