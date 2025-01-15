package br.com.cinepoti.cinepoti_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import br.com.cinepoti.cinepoti_api.dto.request.CinemaRoomRequestDTO;
import br.com.cinepoti.cinepoti_api.dto.response.CinemaRoomResponseDTO;
import br.com.cinepoti.cinepoti_api.service.CinemaRoomService;

import java.util.List;

@RestController
@RequestMapping("/cinemas") 
@CrossOrigin
public class CinemaController {
    private final CinemaRoomService cinemaRoomService;

    @Autowired
    public  CinemaController(CinemaRoomService cinemaRoomService){
        this.cinemaRoomService = cinemaRoomService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<CinemaRoomResponseDTO> createCinemaRoom(@RequestBody CinemaRoomRequestDTO cinemaRoomRequestDTO){
      CinemaRoomResponseDTO cinemaRoom = this.cinemaRoomService.createCinemaRoom(cinemaRoomRequestDTO);
      return new ResponseEntity<>(cinemaRoom, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CinemaRoomResponseDTO>> getAllCinemaRooms() {
        List<CinemaRoomResponseDTO> movies = this.cinemaRoomService.getAllCinemaRooms();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CinemaRoomResponseDTO> getCinemaRoomById(@PathVariable("id") Long id) {
        CinemaRoomResponseDTO movie = this.cinemaRoomService.getCinemaRoomById(id);
        if (movie != null) {
            return new ResponseEntity<>(movie, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<CinemaRoomResponseDTO> updateCinemaRoom(
            @PathVariable("id") Long id,
            @RequestBody CinemaRoomRequestDTO userRequestDTO) {

        CinemaRoomResponseDTO userResponseDTO = this.cinemaRoomService.updateCinemaRoom(id, userRequestDTO);
        if (userResponseDTO != null) {
            return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCinemaRoom(@PathVariable("id") Long id) {
        this.cinemaRoomService.deleteCinemaRoom(id);
        return ResponseEntity.ok().build();
    }
}
