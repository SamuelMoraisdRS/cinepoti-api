package br.com.cinepoti.cinepoti_api.dto.request;

import jakarta.persistence.Column;

public record ResourceRequestDTO(
        @Column(nullable = false)
        String name,

        @Column(nullable = false)
        String key
) {}
