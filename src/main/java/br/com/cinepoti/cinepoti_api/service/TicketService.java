package br.com.cinepoti.cinepoti_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.cinepoti.cinepoti_api.dto.request.TicketRequestDTO;
import br.com.cinepoti.cinepoti_api.dto.response.TicketResponseDTO;
import br.com.cinepoti.cinepoti_api.mapper.TicketMapper;
import br.com.cinepoti.cinepoti_api.model.Seat;
import br.com.cinepoti.cinepoti_api.model.Ticket;
import br.com.cinepoti.cinepoti_api.repository.TicketRepository;

@Service
public class TicketService {
  private final TicketRepository ticketRepository;
  private final SeatService seatService;

  public TicketService(TicketRepository ticketRepository, SeatService seatService) {
    this.ticketRepository = ticketRepository;
    this.seatService = seatService;
  }

  public List<TicketResponseDTO> getAllTicketsByBooking(Long bookingId) {
    return ticketRepository.findAllByBookingId(bookingId).get().stream()
        .map((ticket) -> TicketMapper.toResponseDTO(ticket)).toList();
  }

  public TicketResponseDTO getTicketById(Long id) {
    return TicketMapper.toResponseDTO(ticketRepository.findById(id).orElseThrow());
  }

  public TicketResponseDTO create(Ticket ticket) {
    ticketRepository.save(ticket);
    return TicketMapper.toResponseDTO(ticket);
  }

  public TicketResponseDTO update(Long id, TicketRequestDTO ticketDTO) {
    Ticket ticket = getTicketObjById(id);
    ticket.setSeat(seatService.getObjById(id));
    ticketRepository.save(ticket);
    return TicketMapper.toResponseDTO(ticket);
  }

  public Ticket getTicketObjById(Long id) {
    return ticketRepository.findById(id).orElseThrow();
  }

  public void deleteTicket(Long ticketId) {
    ticketRepository.deleteById(ticketId);
  }

  public Seat getTicketSeatObj(Long ticketId) {
    Ticket ticket = ticketRepository.findById(ticketId).orElseThrow();
    return seatService.getObjById(ticket.getSeat().getId());
  }

  public TicketResponseDTO getTicket(Long ticketId) {
    return TicketMapper.toResponseDTO(ticketRepository.findById(ticketId).orElseThrow());
  }
}
