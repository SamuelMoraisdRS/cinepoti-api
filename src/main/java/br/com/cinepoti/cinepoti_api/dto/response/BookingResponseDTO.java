package br.com.cinepoti.cinepoti_api.dto.response;

import br.com.cinepoti.cinepoti_api.enums.BookingStatus;

import java.time.LocalDate;

public record BookingResponseDTO(
        Long id,
        Long userId,
        Long exhibitionId,
        LocalDate bookingDate,
        Double totalAmount,
        BookingStatus status  // Could be an enum: CONFIRMED, PENDING, CANCELLED
) {}
