package br.com.cinepoti.cinepoti_api.dto.response;

import br.com.cinepoti.cinepoti_api.model.Booking;
import br.com.cinepoti.cinepoti_api.model.Seat;

public record BookTicketResponseDTO(
        Long id,
        Booking booking,
        Seat seat
) {}
