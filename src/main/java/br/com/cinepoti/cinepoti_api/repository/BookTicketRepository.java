package br.com.cinepoti.cinepoti_api.repository;

import br.com.cinepoti.cinepoti_api.model.BookTicket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookTicketRepository extends JpaRepository<BookTicket, Long> {
}
