package br.com.cinepoti.cinepoti_api.mapper;

import br.com.cinepoti.cinepoti_api.model.Booking;
import br.com.cinepoti.cinepoti_api.model.User;
import br.com.cinepoti.cinepoti_api.model.Exhibition;
import br.com.cinepoti.cinepoti_api.model.Seat;
import br.com.cinepoti.cinepoti_api.model.Ticket;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import br.com.cinepoti.cinepoti_api.dto.request.BookingRequestDTO;
import br.com.cinepoti.cinepoti_api.dto.request.TicketRequestDTO;
import br.com.cinepoti.cinepoti_api.dto.response.BookingResponseDTO;
import br.com.cinepoti.cinepoti_api.dto.response.PaymentResponseDTO;
import br.com.cinepoti.cinepoti_api.dto.response.TicketResponseDTO;
import br.com.cinepoti.cinepoti_api.enums.BookingStatus;
import br.com.cinepoti.cinepoti_api.util.Converter;
import br.com.cinepoti.cinepoti_api.util.MapTransformer;

public class BookingMapper {

    /**
     * Cria uma entidade referente ao BookingRequestDTO enviado, instanciando também
     * os Tickets embutidos na requisição. Esse método é usado
     * apenas na criação de novos registros de Bookings na database, para criar uma
     * instância a partir de um registro já existente, consulte a camada
     * service.
     *
     * @param bookingRequestDTO
     * @param user
     * @param exhibition
     * @param ticketsSeats      Map contendo as instancias dos Seats associados a
     *                          cada Ticket, com seus IDs como respectivas chaves.
     * @return Nova instância de objeto Booking, já composto por seus respectivos
     *         Tickets
     */
    public static Booking toEntity(
            BookingRequestDTO bookingRequestDTO,
            User user,
            Exhibition exhibition,
            Map<Long, Seat> ticketsSeats) {
        if (bookingRequestDTO == null) {
            return null;
        }

        BookingStatus bookingStatus = bookingRequestDTO.status() == null ? BookingStatus.PENDING
                : Converter.stringToEnum(BookingStatus.class, bookingRequestDTO.status());

        Booking bookingInstance = new Booking(
                null, // ID will be automatically generated in the database
                user,
                exhibition,
                bookingStatus,
                bookingRequestDTO.bookingDate());

        Map<Long, Ticket> tickets = MapTransformer.transformMap(bookingRequestDTO.tickets(), value -> {
            Seat seat = ticketsSeats.get(value.seatId());
            return TicketMapper.toEntity(value, bookingInstance, seat);
        });

        Double totalAmount = 0.;
        for (Ticket ticket : tickets.values()) {
            totalAmount += ticket.getSeat().getPrice();
        }

        // Create a Booking entity using the user and exhibition fetched beforehand
        bookingInstance.setTickets(tickets);
        bookingInstance.setTotalAmount(totalAmount);

        return bookingInstance;
    }

    // Converts Booking entity to BookingResponseDTO
    public static BookingResponseDTO toResponseDTO(Booking booking) {
        if (booking == null) {
            return null;
        }

        Map<Long, TicketResponseDTO> ticketResponseDTOMap = new HashMap<>();

        for (Entry<Long, Ticket> entry : booking.getTickets().entrySet()) {
            ticketResponseDTOMap.put(entry.getKey(), TicketMapper.toResponseDTO(entry.getValue()));
        }

        PaymentResponseDTO paymentResponseDTO = PaymentMapper.toResponseDTO(booking.getPayment());
        // Create the BookingResponseDTO using data from the Booking entity
        return new BookingResponseDTO(
                booking.getId(),
                booking.getUser().getId(),
                booking.getExhibition().getId(),
                booking.getBookingDate(),
                ticketResponseDTOMap,
                booking.getTotalAmount(),
                paymentResponseDTO,
                booking.getStatus());
    }
}
