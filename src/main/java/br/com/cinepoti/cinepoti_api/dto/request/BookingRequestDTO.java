package br.com.cinepoti.cinepoti_api.dto.request;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record BookingRequestDTO(
        @NotNull(message = "User ID is required")
        Long userId,

        @NotNull(message = "Exhibition ID is required")
        Long exhibitionId,

        @NotNull(message = "Booking date is required")
        LocalDate bookingDate,

        @NotNull(message = "Total amount is required")
        Double totalAmount,

        @NotNull(message = "Booking status is required")
        String status
) {}
