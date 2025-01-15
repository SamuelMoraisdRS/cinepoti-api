package br.com.cinepoti.cinepoti_api.dto.response;


public record TicketResponseDTO(
        Long id,
        Long bookingId,
        Long seatId
) {}
