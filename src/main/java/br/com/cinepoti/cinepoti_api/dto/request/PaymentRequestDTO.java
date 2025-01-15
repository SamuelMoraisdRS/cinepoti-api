package br.com.cinepoti.cinepoti_api.dto.request;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record PaymentRequestDTO(
        String paymentStatus,

        @NotNull(message = "Payment method is required")
        String paymentMethod,  // Could be an enum: CARD, PIX, CASH

        @NotNull(message = "Payment date is required")
        LocalDate paymentDate
) {}
