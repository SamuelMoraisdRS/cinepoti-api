package br.com.cinepoti.cinepoti_api.mapper;

import br.com.cinepoti.cinepoti_api.model.BookTicket;
import br.com.cinepoti.cinepoti_api.dto.request.BookTicketRequestDTO;
import br.com.cinepoti.cinepoti_api.dto.response.BookTicketResponseDTO;
import br.com.cinepoti.cinepoti_api.model.Booking;
import br.com.cinepoti.cinepoti_api.model.Seat;

public class BookTicketMapper {

    // Converts BookTicketRequestDTO to BookTicket entity
    public static BookTicket toEntity(BookTicketRequestDTO bookTicketRequestDTO, Booking booking, Seat seat) {
        if (bookTicketRequestDTO == null) {
            return null;
        }

        return new BookTicket(
                null, // ID will be generated by the database
                seat,
                booking
        );
    }

    // Converts BookTicket entity to BookTicketResponseDTO
    public static BookTicketResponseDTO toResponseDTO(BookTicket bookTicket) {
        if (bookTicket == null) {
            return null;
        }

        return new BookTicketResponseDTO(
                bookTicket.getId(),
                bookTicket.getBooking(),
                bookTicket.getSeat()
        );
    }
}
