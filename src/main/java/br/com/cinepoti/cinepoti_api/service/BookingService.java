package br.com.cinepoti.cinepoti_api.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import br.com.cinepoti.cinepoti_api.dto.request.BookingRequestDTO;
import br.com.cinepoti.cinepoti_api.dto.request.PaymentRequestDTO;
import br.com.cinepoti.cinepoti_api.dto.request.TicketRequestDTO;
import br.com.cinepoti.cinepoti_api.dto.response.BookingResponseDTO;
import br.com.cinepoti.cinepoti_api.dto.response.PaymentResponseDTO;
import br.com.cinepoti.cinepoti_api.enums.BookingStatus;
import br.com.cinepoti.cinepoti_api.enums.PaymentStatus;
import br.com.cinepoti.cinepoti_api.exception.ResourceNotFoundException;
import br.com.cinepoti.cinepoti_api.mapper.BookingMapper;
import br.com.cinepoti.cinepoti_api.mapper.PaymentMapper;
import br.com.cinepoti.cinepoti_api.model.Booking;
import br.com.cinepoti.cinepoti_api.model.Exhibition;
import br.com.cinepoti.cinepoti_api.model.Payment;
import br.com.cinepoti.cinepoti_api.model.Seat;
import br.com.cinepoti.cinepoti_api.model.Ticket;
import br.com.cinepoti.cinepoti_api.model.User;
import br.com.cinepoti.cinepoti_api.repository.BookingRepository;
import br.com.cinepoti.cinepoti_api.util.MapTransformer;

@Service
public class BookingService {

  private final BookingRepository bookingRepository;
  private final UserService userService;
  private final ExhibitionService exhibitionService;
  private final SeatService seatService;
  private final TicketService ticketService;
  private final PaymentService paymentService;

  public BookingService(BookingRepository bookingRepository, UserService userService,
      ExhibitionService exhibitionService, SeatService seatService, TicketService ticketService,
      PaymentService paymentService) {
    this.bookingRepository = bookingRepository;
    this.userService = userService;
    this.exhibitionService = exhibitionService;
    this.seatService = seatService;
    this.ticketService = ticketService;
    this.paymentService = paymentService;
  }

  /*
   * Métodos que retornam DTOs são exclusivos aos controllers, os que retornam
   * instancias das entidades são usados nas outras camadas.
   */

  public BookingResponseDTO create(BookingRequestDTO bookingDTO, Long userId) {

    User user = userService.getUserObjectById(userId);

    // Consideramos que a exibicao e sala ja existem na database
    Exhibition exhibition = exhibitionService.getExhibitionObjById(bookingDTO.exhibitionId());

    // Recuperar as instancias de Seats. Supondo que já existem no banco de dados
    Map<Long, Seat> ticketsSeats = MapTransformer.transformMap(bookingDTO.tickets(),
        value -> seatService.getObjById(value.seatId()));

    Booking booking = BookingMapper.toEntity(bookingDTO, user, exhibition, ticketsSeats);

    bookingRepository.save(booking);

    for (Ticket ticket : booking.getTickets().values()) {
      ticketService.create(ticket);
    }

    return BookingMapper.toResponseDTO(booking);

  }

  public List<BookingResponseDTO> getAllUsersBookings(Long userId) {
    List<Booking> bookings = bookingRepository.findAllByUserId(userId)
        .orElseThrow(() -> new ResourceNotFoundException("Bookings not found for user: " + userId));
    return bookings.stream().map(BookingMapper::toResponseDTO).toList();
  }

  public BookingResponseDTO getBookingById(Long bookingId) {
    return BookingMapper.toResponseDTO(bookingRepository.findById(bookingId).orElseThrow());
  }

  public Booking getBookingObjById(Long bookingId) {
    return bookingRepository.findById(bookingId).orElseThrow();
  }

  public BookingResponseDTO updateBooking(Long id, BookingRequestDTO bookingDTO) {
    // Instancia atual do objeto Booking
    Booking updatedBooking = bookingRepository.findById(id).orElseThrow();

    updatedBooking.setExhibition(exhibitionService.getExhibitionObjById(id));
    updatedBooking.setBookingDate(bookingDTO.bookingDate());
    bookingDTO.tickets().entrySet().stream().forEach(entry -> ticketService.update(entry.getKey(), entry.getValue()));
    bookingRepository.save(updatedBooking);
    return BookingMapper.toResponseDTO(updatedBooking);
  }

  public BookingResponseDTO cancelBooking(Long id) {
    Booking booking = bookingRepository.findById(id).orElseThrow();
    booking.setStatus(BookingStatus.CANCELLED);
    bookingRepository.save(booking);
    return BookingMapper.toResponseDTO(booking);
  }

  public BookingResponseDTO cancelTicket(Long ticketId) {
    Ticket ticket = ticketService.getTicketObjById(ticketId);
    Booking booking = bookingRepository.findById(ticket.getBooking().getId()).orElseThrow();
    booking.getTickets().remove(ticketId);
    ticketService.deleteTicket(ticketId);
    bookingRepository.save(booking);
    return BookingMapper.toResponseDTO(booking);
  }

  public PaymentResponseDTO getBookingPayment(Long bookingId) {
    return PaymentMapper.toResponseDTO(bookingRepository.findById(bookingId).orElseThrow().getPayment());
  }

  public BookingResponseDTO pay(Long bookingId, PaymentRequestDTO paymentDTO) {
    Booking booking = getBookingObjById(bookingId);
    Payment payment = PaymentMapper.toEntity(paymentDTO, booking, booking.getTotalAmount());
    payment.setPaymentStatus(PaymentStatus.PAID);
    paymentService.create(payment);
    booking.setStatus(BookingStatus.CONFIRMED);
    booking.setPayment(payment);
    bookingRepository.save(booking);
    return BookingMapper.toResponseDTO(booking);
  }

  public Double calculateTotalPrice(Booking booking) {
    Double totalPrice = 0.;
    for (Ticket ticket : booking.getTickets().values()) {
      totalPrice += ticket.getSeat().getPrice();
    }
    return totalPrice;
  }
}
