package br.com.cinepoti.cinepoti_api.mapper;

import br.com.cinepoti.cinepoti_api.model.Address;
import br.com.cinepoti.cinepoti_api.model.CinemaRoom;
import br.com.cinepoti.cinepoti_api.dto.request.CinemaRoomRequestDTO;
import br.com.cinepoti.cinepoti_api.dto.response.CinemaRoomResponseDTO;



public class CinemaRoomMapper {

    // Converts CinemaRoomRequestDTO to CinemaRoom entity
    public static CinemaRoom toEntity(CinemaRoomRequestDTO cinemaRoomRequestDTO, Address address) {
        if (cinemaRoomRequestDTO == null) {
            return null;
        }

        // Create a CinemaRoom entity from the DTO
        return new CinemaRoom(
                null, // ID will be automatically generated in the database
                address,
                cinemaRoomRequestDTO.name()
        );
    }

    // Converts CinemaRoom entity to CinemaRoomResponseDTO
    public static CinemaRoomResponseDTO toResponseDTO(CinemaRoom cinemaRoom) {
        if (cinemaRoom == null) {
            return null;
        }

        // Create the CinemaRoomResponseDTO using data from the CinemaRoom entity
        return new CinemaRoomResponseDTO(
                cinemaRoom.getId(),
                cinemaRoom.getName(),
                cinemaRoom.getAddress()
        );
    }
}
