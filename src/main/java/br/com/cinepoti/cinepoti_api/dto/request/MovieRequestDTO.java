package br.com.cinepoti.cinepoti_api.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record MovieRequestDTO(
        @NotNull(message = "Title is required")
        @Size(min = 1, max = 100, message = "Title must be between 1 and 100 characters")
        String title,

        @NotNull(message = "Duration is required")
        Integer duration,  // Duration in minutes

        @NotNull(message = "Release date is required")
        LocalDate releaseDate,

        Double rating,  // Rating (optional)

        String synopsis  // Optional
) {}
