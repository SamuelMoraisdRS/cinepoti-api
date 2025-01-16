package br.com.cinepoti.cinepoti_api.service;

import br.com.cinepoti.cinepoti_api.dto.request.GenreRequestDTO;
import br.com.cinepoti.cinepoti_api.dto.response.GenreResponseDTO;
import br.com.cinepoti.cinepoti_api.mapper.GenreMapper;
import br.com.cinepoti.cinepoti_api.model.Genre;
import br.com.cinepoti.cinepoti_api.repository.GenreRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreService {
    private final GenreRepository genreRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public GenreResponseDTO createGenre(GenreRequestDTO requestDTO){
        Genre genre = GenreMapper.toEntity(requestDTO);
        return GenreMapper.toResponseDTO(genreRepository.save(genre));
    }

    // TODO: Paginação de consulta
    public List<Genre> getAllGenres() {
        return genreRepository.findAll().stream()
                .collect(Collectors.toList());
    }

    public Genre getGenreById(Long id) {
        return genreRepository.findById(id)
                .orElse(null);
    }

}