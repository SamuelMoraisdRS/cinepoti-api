package br.com.cinepoti.cinepoti_api.dto.response;

import br.com.cinepoti.cinepoti_api.enums.BookingStatus;

import java.time.LocalDate;
import java.util.Map;

public record BookingResponseDTO(
        Long id,
        Long userId,
        Long exhibitionId,
        LocalDate bookingDate,
        Map<Long,TicketResponseDTO> tickets,
        Double totalAmount,
        PaymentResponseDTO payment,
        BookingStatus status  // Could be an enum: CONFIRMED, PENDING, CANCELLED
) {}
