package br.com.cinepoti.cinepoti_api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.cinepoti.cinepoti_api.dto.request.ExhibitionRequestDTO;
import br.com.cinepoti.cinepoti_api.dto.response.ExhibitionResponseDTO;
import br.com.cinepoti.cinepoti_api.dto.response.SeatResponseDTO;
import br.com.cinepoti.cinepoti_api.mapper.ExhibitionMapper;
import br.com.cinepoti.cinepoti_api.mapper.SeatMapper;
import br.com.cinepoti.cinepoti_api.model.CinemaRoom;
import br.com.cinepoti.cinepoti_api.model.Exhibition;
import br.com.cinepoti.cinepoti_api.model.Movie;
import br.com.cinepoti.cinepoti_api.repository.CinemaRoomRepository;
import br.com.cinepoti.cinepoti_api.repository.ExhibitionRepository;
import br.com.cinepoti.cinepoti_api.repository.MovieRepository;
import br.com.cinepoti.cinepoti_api.repository.SeatRepository;
import org.springframework.stereotype.Service;

@Service
public class ExhibitionService {
  private final ExhibitionRepository exhibitionRepository;
  private final MovieRepository movieRepository;
  private final CinemaRoomRepository cinemaRoomRepository;
  private final SeatRepository seatRepository;

  @Autowired
  public ExhibitionService(ExhibitionRepository exhibitionRepository, MovieRepository movieRepository,
      CinemaRoomRepository cinemaRoomRepository, SeatRepository seatRepository) {
    this.exhibitionRepository = exhibitionRepository;
    this.movieRepository = movieRepository;
    this.cinemaRoomRepository = cinemaRoomRepository;
    this.seatRepository = seatRepository;
  }

  public ExhibitionResponseDTO createExhibition(ExhibitionRequestDTO exhibitionRequest) {
    Exhibition exhibition = ExhibitionMapper.toEntity(exhibitionRequest, null, null);
    exhibition = this.exhibitionRepository.save(exhibition);

    return ExhibitionMapper.toResponseDTO(exhibition);
  }

  public List<ExhibitionResponseDTO> getAllExhibitions() {
    List<Exhibition> exhibitions = this.exhibitionRepository.findAll();

    return exhibitions.stream()
        .map(ExhibitionMapper::toResponseDTO)
        .collect(Collectors.toList());
  }

  public ExhibitionResponseDTO getExhibitionById(Long id) {
    return this.exhibitionRepository.findById(id)
        .map(ExhibitionMapper::toResponseDTO)
        .orElse(null);
  }

  public List<SeatResponseDTO> getExhibitionAvailableSeats(Long exhibitionId) {
    Exhibition exhibition = this.exhibitionRepository.findById(exhibitionId)
        .orElse(null);

    if (exhibition == null)
      return null;

    return seatRepository.findAvailableSeats(exhibition.getCinemaRoom().getId(), exhibitionId)
        .stream()
        .map(SeatMapper::toResponseDTO)
        .collect(Collectors.toList());
  }

  public ExhibitionResponseDTO updateExhibition(Long id, ExhibitionRequestDTO exhibitionRequestDTO) {
    return this.exhibitionRepository.findById(id)
        .map(exhibition -> {
          Long movieId = exhibitionRequestDTO.movieId(), cinemaRoomId = exhibitionRequestDTO.cinemaRoomId();

          if (movieId != null && movieId > 0 && this.movieRepository.existsById(movieId))
            exhibition.setMovie(new Movie(movieId));

          if (cinemaRoomId != null && cinemaRoomId > 0 && this.cinemaRoomRepository.existsById(cinemaRoomId))
            exhibition.setCinemaRoom(new CinemaRoom(cinemaRoomId));

          Exhibition updatedExhibition = this.exhibitionRepository.save(exhibition);
          return ExhibitionMapper.toResponseDTO(updatedExhibition);
        })
        .orElse(null);
  }

  // Deleta um usuário por ID
  public void deleteExhibition(Long id) {
    // TODO: Verificar se existe algum ingresso comprado
    if (!this.exhibitionRepository.existsById(id)) {
      throw new RuntimeException("Exhibition not found with id: " + id);
    }
    this.exhibitionRepository.deleteById(id);
  }
}
