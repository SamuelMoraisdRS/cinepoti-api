package br.com.cinepoti.cinepoti_api.dto.request;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record PaymentRequestDTO(
        @NotNull(message = "Amount is required")
        Double amount,

        @NotNull(message = "Payment method is required")
        String paymentMethod,  // Could be an enum: CARD, PIX, CASH

        @NotNull(message = "Payment status is required")
        String paymentStatus,  // Could be an enum: PAID, PENDING, FAILED

        @NotNull(message = "Booking ID is required")
        Long bookingId,

        @NotNull(message = "Payment date is required")
        LocalDate paymentDate
) {}
