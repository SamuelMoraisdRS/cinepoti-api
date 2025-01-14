package br.com.cinepoti.cinepoti_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.cinepoti.cinepoti_api.dto.request.ExhibitionRequestDTO;
import br.com.cinepoti.cinepoti_api.dto.response.ExhibitionResponseDTO;
import br.com.cinepoti.cinepoti_api.dto.response.SeatResponseDTO;
import br.com.cinepoti.cinepoti_api.service.ExhibitionService;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/filmes")
@CrossOrigin
public class ExhibitionController {
  private final ExhibitionService exhibitionService;

  @Autowired
  public ExhibitionController(ExhibitionService exhibitionService) {
    this.exhibitionService = exhibitionService;
  }

  @PostMapping
  public ResponseEntity<ExhibitionResponseDTO> createExhibition(@Valid @RequestBody ExhibitionRequestDTO exhibitionRequestDTO) {
    ExhibitionResponseDTO exhibitionResponseDTO = this.exhibitionService.createExhibition(exhibitionRequestDTO);

    return new ResponseEntity<>(exhibitionResponseDTO, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<ExhibitionResponseDTO>> getAllExhibitions() {
    List<ExhibitionResponseDTO> exhibitions = this.exhibitionService.getAllExhibitions();
    return new ResponseEntity<>(exhibitions, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ExhibitionResponseDTO> getExhibitionById(@PathVariable("id") Long id) {
    ExhibitionResponseDTO exhibition = this.exhibitionService.getExhibitionById(id);
    if (exhibition != null) {
      return new ResponseEntity<>(exhibition, HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @GetMapping("/{id}/assentos")
  public ResponseEntity<List<SeatResponseDTO>> getExhibitionAvailableSeats(@PathVariable("id") Long id) {
    List<SeatResponseDTO> exhibition = this.exhibitionService.getExhibitionAvailableSeats(id);
    if (exhibition == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    return new ResponseEntity<>(exhibition, HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ExhibitionResponseDTO> updateExhibition(
      @PathVariable("id") Long id,
      @RequestBody ExhibitionRequestDTO userRequestDTO) {

    ExhibitionResponseDTO userResponseDTO = this.exhibitionService.updateExhibition(id, userRequestDTO);
    if (userResponseDTO != null) {
      return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteExhibition(@PathVariable("id") Long id) {
    this.exhibitionService.deleteExhibition(id);
    return ResponseEntity.ok().build();
  }
}
