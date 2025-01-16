package br.com.cinepoti.cinepoti_api.dto.response;


import java.time.LocalDate;

public record PaymentResponseDTO(
        Long id,
        Double amount,
        String paymentMethod,
        String paymentStatus,
        Long bookingId,
        LocalDate paymentDate
) {}
