package br.com.cinepoti.cinepoti_api.service;

import br.com.cinepoti.cinepoti_api.dto.request.CinemaRoomRequestDTO;
import br.com.cinepoti.cinepoti_api.dto.response.CinemaRoomResponseDTO;
import br.com.cinepoti.cinepoti_api.mapper.CinemaRoomMapper;
import br.com.cinepoti.cinepoti_api.model.Address;
import br.com.cinepoti.cinepoti_api.model.CinemaRoom;
import br.com.cinepoti.cinepoti_api.repository.CinemaRoomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CinemaRoomService {
  private final CinemaRoomRepository cinemaRoomRepository;

  @Autowired
  public CinemaRoomService(CinemaRoomRepository cinemaRoomRepository) {
    this.cinemaRoomRepository = cinemaRoomRepository;
  }

  public CinemaRoomResponseDTO createCinemaRoom(CinemaRoomRequestDTO cinemaRoomRequestDTO){
    CinemaRoom cinemaRoom = new CinemaRoom(
      null,
      new Address(cinemaRoomRequestDTO.addressId()),
      cinemaRoomRequestDTO.name()
    );

    cinemaRoom.setSeatsWithDTO(cinemaRoomRequestDTO.seats());

    cinemaRoom = this.cinemaRoomRepository.save(cinemaRoom);
    return CinemaRoomMapper.toResponseDTO(cinemaRoom);
  }

  // TODO: Paginação de consulta
  public List<CinemaRoomResponseDTO> getAllCinemaRooms() {
    return cinemaRoomRepository.findAll().stream()
        .map(CinemaRoomMapper::toResponseDTO)
        .collect(Collectors.toList());
  }

  public CinemaRoomResponseDTO getCinemaRoomById(Long id) {
    CinemaRoom cinemaRoom = cinemaRoomRepository.findById(id)
        .orElse(null);

    return CinemaRoomMapper.toResponseDTO(cinemaRoom);
  }

  public CinemaRoomResponseDTO updateCinemaRoom(Long id, CinemaRoomRequestDTO cinemaRoomRequestDTO) {
    return this.cinemaRoomRepository.findById(id)
        .map(cinemaRoom -> {
          cinemaRoom.setName(cinemaRoomRequestDTO.name());
          // TODO: Atualização de endereço

          CinemaRoom updatedCinemaRoom = this.cinemaRoomRepository.save(cinemaRoom);
          return CinemaRoomMapper.toResponseDTO(updatedCinemaRoom);
        })
        .orElse(null);
  }

  // Deleta um usuário por ID
  public void deleteCinemaRoom(Long id) {
    // TODO: Verificar se existe alguma exibição relacionada
    if (!this.cinemaRoomRepository.existsById(id)) {
      throw new RuntimeException("Movie not found with id: " + id);
    }
    this.cinemaRoomRepository.deleteById(id);
  }
}