package br.com.cinepoti.cinepoti_api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.cinepoti.cinepoti_api.dto.request.BookingRequestDTO;
import br.com.cinepoti.cinepoti_api.dto.request.PaymentRequestDTO;
import br.com.cinepoti.cinepoti_api.dto.response.BookingResponseDTO;
import br.com.cinepoti.cinepoti_api.dto.response.PaymentResponseDTO;
import br.com.cinepoti.cinepoti_api.dto.response.TicketResponseDTO;
import br.com.cinepoti.cinepoti_api.service.BookingService;
import br.com.cinepoti.cinepoti_api.service.TicketService;
import br.com.cinepoti.cinepoti_api.service.UserDetailsImpl;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("compras/")
public class BookingController {

  private final BookingService bookingService;

  private final TicketService ticketService;

  public BookingController(BookingService bookingService, TicketService ticketService) {
    this.bookingService = bookingService;
    this.ticketService = ticketService;
  }

  @PreAuthorize("hasAuthority('COMMON')")
  @PostMapping("")
  public ResponseEntity<BookingResponseDTO> createBooking(@Valid @RequestBody BookingRequestDTO bookingDTO,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    BookingResponseDTO response = bookingService.create(bookingDTO, userDetails.getId());
    return ResponseEntity.ok().body(response);
  }

  @GetMapping("")
  public ResponseEntity<List<BookingResponseDTO>> getUsersBookings(
      @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
    return ResponseEntity.ok().body(bookingService.getAllUsersBookings(userDetailsImpl.getId()));
  }

  @GetMapping("{id}/ingressos/")
  public ResponseEntity<List<TicketResponseDTO>> getAllTicketsByBooking(@PathVariable("id") Long bookingId) {
    return ResponseEntity.ok().body(ticketService.getAllTicketsByBooking(bookingId));
  }

  @GetMapping("/ingressos/{ticketId}")
  public ResponseEntity<TicketResponseDTO> getTicket(@PathVariable("ticketId") Long ticketId) {
    return ResponseEntity.ok().body(ticketService.getTicketById(ticketId));
  }

  @GetMapping("{id}/")
  public ResponseEntity<BookingResponseDTO> getBooking(@PathVariable("id") Long bookingId) {
    return ResponseEntity.ok().body(bookingService.getBookingById(bookingId));
  }

  @GetMapping("{id}/pagamento")
  public ResponseEntity<PaymentResponseDTO> getBookingPayment(@PathVariable Long bookingId) {
      return ResponseEntity.ok().body(bookingService.getBookingPayment(bookingId));
  }

  @PutMapping("{bookingId}/pagar")
public ResponseEntity<BookingResponseDTO> payBooking(@PathVariable("bookingId") Long id, @RequestBody PaymentRequestDTO payment) {
    return ResponseEntity.ok().body(bookingService.pay(id, payment));

  }

  @PutMapping("editar/{id}")
  public ResponseEntity<BookingResponseDTO> updateBooking(@PathVariable("id") Long id,
      @Valid @RequestBody BookingRequestDTO bookingRequestDTO) {
    return ResponseEntity.ok().body(bookingService.updateBooking(id, bookingRequestDTO));
  }

  // ? - É necessário fazer alguma verificação de segurança a mais?
  @PutMapping("cancelar/{id}")
  public ResponseEntity<BookingResponseDTO> cancelBooking(@PathVariable("id") Long id) {
    return ResponseEntity.ok().body(bookingService.cancelBooking(id));
  }

  /*
   * Estou considerando que tem uma composição forte dos Tickets com os Bookings (os tickets não existem
   * isolados dos seus Bookings), por isso acho válido delegar o gerenciamento dos tickets totalmente aos
   * Bookings. Se acharem ruim, posso mudar a abordagem.
   */
  @PutMapping("cancelar/ingresso/")
  public void cancelTicket( @Valid @RequestBody List<Long> ticketIds) {
    for (Long ticketId : ticketIds) {
      bookingService.cancelTicket(ticketId);
    }
  }

}
