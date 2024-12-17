package br.com.cinepoti.cinepoti_api.dto.request;

import jakarta.persistence.Column;

public record ProfileRequestDTO(
        @Column(nullable = false)
        String description
) { }
