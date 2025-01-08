package br.com.cinepoti.cinepoti_api.dto.request;

import jakarta.validation.constraints.NotNull;

public record BookTicketRequestDTO(
        @NotNull(message = "Booking ID is required")
        Long bookingId,

        @NotNull(message = "Seat ID is required")
        Long seatId
) {}
