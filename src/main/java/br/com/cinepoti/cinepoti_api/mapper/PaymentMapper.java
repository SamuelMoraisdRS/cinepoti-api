package br.com.cinepoti.cinepoti_api.mapper;

import br.com.cinepoti.cinepoti_api.enums.PaymentMethod;
import br.com.cinepoti.cinepoti_api.enums.PaymentStatus;
import br.com.cinepoti.cinepoti_api.model.Booking;
import br.com.cinepoti.cinepoti_api.model.Payment;
import br.com.cinepoti.cinepoti_api.dto.request.PaymentRequestDTO;
import br.com.cinepoti.cinepoti_api.dto.response.PaymentResponseDTO;
import br.com.cinepoti.cinepoti_api.util.Converter;

public class PaymentMapper {

    public static Payment toEntity(PaymentRequestDTO paymentRequestDTO, Booking booking) {
        if (paymentRequestDTO == null || booking == null) {
            return null;
        }

        return new Payment(
                null, // ID is auto-generated
                booking,
                Converter.stringToEnum(PaymentStatus.class, paymentRequestDTO.paymentStatus()),
                Converter.stringToEnum(PaymentMethod.class, paymentRequestDTO.paymentMethod()),
                paymentRequestDTO.amount(),
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
                payment.getBooking(),
                payment.getPaymentDate()
                );
    }
}
