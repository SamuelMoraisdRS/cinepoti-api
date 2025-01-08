package br.com.cinepoti.cinepoti_api.mapper;

import br.com.cinepoti.cinepoti_api.model.Booking;
import br.com.cinepoti.cinepoti_api.model.User;
import br.com.cinepoti.cinepoti_api.model.Exhibition;
import br.com.cinepoti.cinepoti_api.dto.request.BookingRequestDTO;
import br.com.cinepoti.cinepoti_api.dto.response.BookingResponseDTO;
import br.com.cinepoti.cinepoti_api.enums.BookingStatus;
import br.com.cinepoti.cinepoti_api.util.Converter;

public class BookingMapper {

    // Converts BookingRequestDTO to Booking entity
    public static Booking toEntity(
            BookingRequestDTO bookingRequestDTO,
            User user,
            Exhibition exhibition
    ) {
        if (bookingRequestDTO == null) {
            return null;
        }



        // Create a Booking entity using the user and exhibition fetched beforehand
        return new Booking(
                null, // ID will be automatically generated in the database
                user,
                exhibition,
                Converter.stringToEnum(BookingStatus.class, bookingRequestDTO.status()),
                bookingRequestDTO.bookingDate(),
                bookingRequestDTO.totalAmount()
        );
    }

    // Converts Booking entity to BookingResponseDTO
    public static BookingResponseDTO toResponseDTO(Booking booking) {
        if (booking == null) {
            return null;
        }

        // Create the BookingResponseDTO using data from the Booking entity
        return new BookingResponseDTO(
                booking.getId(),
                booking.getUser().getId(),
                booking.getExhibition().getId(),
                booking.getBookingDate(),
                booking.getTotalAmount(),
                booking.getStatus()
        );
    }
}
