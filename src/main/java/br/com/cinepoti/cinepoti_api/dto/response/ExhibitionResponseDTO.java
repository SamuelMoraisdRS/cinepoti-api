package br.com.cinepoti.cinepoti_api.dto.response;

import br.com.cinepoti.cinepoti_api.model.CinemaRoom;
import br.com.cinepoti.cinepoti_api.model.Movie;

import java.time.LocalDateTime;

public record ExhibitionResponseDTO(
        Long id,
        Movie movie,
        CinemaRoom cinemaRoom,
        LocalDateTime startTime
) {}
