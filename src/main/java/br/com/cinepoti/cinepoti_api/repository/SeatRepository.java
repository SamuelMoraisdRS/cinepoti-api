package br.com.cinepoti.cinepoti_api.repository;

import br.com.cinepoti.cinepoti_api.model.Seat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SeatRepository extends JpaRepository<Seat, Long> {
  List<Seat> findByCinemaRoomId(Long cinemaRoomId);

  @Query("""
        SELECT s
        FROM CP_SEAT s
        WHERE s.cinemaRoom_id = :cinemaRoomId
        AND s.id NOT IN (
            SELECT bt.seat_id
            FROM CP_BOOK_TICKET bt
            WHERE bt.exhibition_id = :exhibitionId
        )
    """)
  List<Seat> findAvailableSeats(@Param("cinemaRoomId") Long cinemaRoomId, @Param("exhibitionId") Long exhibitionId);
}
