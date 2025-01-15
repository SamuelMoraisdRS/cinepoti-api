package br.com.cinepoti.cinepoti_api.repository;

import br.com.cinepoti.cinepoti_api.model.Ticket;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
  Optional<List<Ticket>> findAllByBookingId(Long bookingID);
}
