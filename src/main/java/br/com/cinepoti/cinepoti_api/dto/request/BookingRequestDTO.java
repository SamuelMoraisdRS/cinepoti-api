package br.com.cinepoti.cinepoti_api.dto.request;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

import java.util.Map;

public record BookingRequestDTO(
        // Poderíamos mudar o campo ID por um outro identificador único, pois o ID expõe informaçoes do banco de dados.
        @NotNull(message = "User ID is required")
        Long userId,

        @NotNull(message = "Exhibition ID is required")
        Long exhibitionId,

        @NotNull(message = "At least one ticket is required")
        Map<Long, TicketRequestDTO> tickets,

        @NotNull(message = "Booking date is required")
        LocalDate bookingDate,

        String status
) {}
