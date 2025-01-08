package br.com.cinepoti.cinepoti_api.dto.response;

import br.com.cinepoti.cinepoti_api.model.Booking;

import java.time.LocalDate;

public record PaymentResponseDTO(
        Long id,
        Double amount,
        String paymentMethod,
        String paymentStatus,
        Booking booking,
        LocalDate paymentDate
) {}
