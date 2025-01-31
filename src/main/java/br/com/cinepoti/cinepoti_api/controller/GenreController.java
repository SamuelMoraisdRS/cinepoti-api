package br.com.cinepoti.cinepoti_api.controller;


import br.com.cinepoti.cinepoti_api.dto.request.GenreRequestDTO;
import br.com.cinepoti.cinepoti_api.dto.response.GenreResponseDTO;
import br.com.cinepoti.cinepoti_api.model.Genre;
import br.com.cinepoti.cinepoti_api.service.GenreService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/generos")
@CrossOrigin
public class GenreController {
    private final GenreService genreService;

    public  GenreController(GenreService genreService){
        this.genreService = genreService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/cadastrar")
    public ResponseEntity<GenreResponseDTO> createUser(@Valid @RequestBody GenreRequestDTO genreRequestDTO) {
        GenreResponseDTO genreResponseDTO = genreService.createGenre(genreRequestDTO);

        return new ResponseEntity<>(genreResponseDTO, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity<List<Genre>> getAllGenres() {
        List<Genre> genres = genreService.getAllGenres();
        return new ResponseEntity<>(genres, HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Genre> getGenreById(@PathVariable("id") Long id) {
        Genre genre = genreService.getGenreById(id);
        if (genre != null) {
            return new ResponseEntity<>(genre, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
