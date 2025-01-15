package br.com.cinepoti.cinepoti_api.dto.request;

import jakarta.validation.constraints.NotNull;

public record TicketRequestDTO(
  @NotNull(message = "Seat ID is required") Long seatId

) {
}
