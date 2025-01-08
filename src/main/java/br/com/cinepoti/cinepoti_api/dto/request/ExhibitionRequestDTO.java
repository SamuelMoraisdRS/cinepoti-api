package br.com.cinepoti.cinepoti_api.dto.request;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record ExhibitionRequestDTO(
        @NotNull(message = "Movie ID is required")
        Long movieId,

        @NotNull(message = "Cinema Room ID is required")
        Long cinemaRoomId,

        @NotNull(message = "Start time is required")
        LocalDateTime startTime
) {}
