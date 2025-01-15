package br.com.cinepoti.cinepoti_api.repository;

import br.com.cinepoti.cinepoti_api.model.CinemaRoom;
import br.com.cinepoti.cinepoti_api.model.Exhibition;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExhibitionRepository extends JpaRepository<Exhibition, Long> {

  Optional<Exhibition> findByCinemaRoomAndStartTime(CinemaRoom cinemaRoom, LocalDateTime startTime);
}
