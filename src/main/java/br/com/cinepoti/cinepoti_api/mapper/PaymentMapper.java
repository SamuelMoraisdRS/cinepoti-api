package br.com.cinepoti.cinepoti_api.mapper;

import br.com.cinepoti.cinepoti_api.enums.PaymentMethod;
import br.com.cinepoti.cinepoti_api.enums.PaymentStatus;
import br.com.cinepoti.cinepoti_api.model.Booking;
import br.com.cinepoti.cinepoti_api.model.Payment;
import br.com.cinepoti.cinepoti_api.dto.request.PaymentRequestDTO;
import br.com.cinepoti.cinepoti_api.dto.response.PaymentResponseDTO;
import br.com.cinepoti.cinepoti_api.util.Converter;

public class PaymentMapper {

    // Temos que receber o amount como argumento pois quem calcula o valor será a entidade Booking
    public static Payment toEntity(PaymentRequestDTO paymentRequestDTO, Booking booking, Double amount) {
        if (paymentRequestDTO == null || booking == null) {
            return null;
        }

        PaymentStatus paymentStatus = paymentRequestDTO.paymentStatus() == null ? PaymentStatus.PENDING :
            Converter.stringToEnum(PaymentStatus.class, paymentRequestDTO.paymentStatus());

        return new Payment(
                null, // ID is auto-generated
                booking,
                paymentStatus,
                Converter.stringToEnum(PaymentMethod.class, paymentRequestDTO.paymentMethod()),
                amount,
                paymentRequestDTO.paymentDate()
        );
    }


    // Converts Payment entity to PaymentResponseDTO
    public static PaymentResponseDTO toResponseDTO(Payment payment) {
        if (payment == null) {
            return null;
        }

        // Create the PaymentResponseDTO using data from the Payment entity
        return new PaymentResponseDTO(
                payment.getId(),
                payment.getAmount(),
                payment.getPaymentMethod().toString(),
                payment.getPaymentStatus().toString(),
                payment.getBooking().getId(),
                payment.getPaymentDate()
                );
    }
}
