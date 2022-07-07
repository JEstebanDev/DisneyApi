package jestebancdev.DisneyApi.services;

import jestebancdev.DisneyApi.dto.MovieSerieDTO;
import jestebancdev.DisneyApi.enumeration.Order;
import jestebancdev.DisneyApi.model.Character;
import jestebancdev.DisneyApi.model.MovieSerie;
import jestebancdev.DisneyApi.repository.ICharacterRepository;
import jestebancdev.DisneyApi.repository.IMovieSerieRepository;
import jestebancdev.DisneyApi.services.interfaces.IMovieSerieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * @author Juan Esteban Castaño Holguin castanoesteban9@gmail.com 7/5/2022
 */
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MovieSerieServiceImp implements IMovieSerieService {
    @Autowired
    private IMovieSerieRepository movieSerieService;
    @Autowired
    private ICharacterRepository characterRepository;

    @Override
    public MovieSerie create(MovieSerie movieSerie) {
        log.info("Create MovieSerie");
        return movieSerieService.save(movieSerie);
    }

    public Collection<MovieSerie> detailMovieSerie(){
        log.info("Detail MovieSerie");
        return movieSerieService.findAll();
    }

    @Override
    public Collection<MovieSerieDTO> read(String title, Long idGender, Order order) {
        log.info("Searching by Parameters MovieSerie");
        boolean withoutParameters = title.equals("") && idGender <= 0 && order == null;
        boolean byTitle = !title.equals("");
        boolean byIdGender = idGender > 0;
        boolean Order = order != null;
        if (withoutParameters) {
            return movieSerieService.findAll().stream()
                    .map(this::convertToMovieSerieDTO)
                    .collect(Collectors.toList());
        }
        if (byTitle) {
            return movieSerieService.findMovieSerieByTitleStartingWith(title).stream()
                    .map(this::convertToMovieSerieDTO)
                    .collect(Collectors.toList());
        }
        if (byIdGender) {
            return movieSerieService.findMovieSerieByIdGender(idGender).stream()
                    .map(this::convertToMovieSerieDTO)
                    .collect(Collectors.toList());
        }
        if (Order) {
            if (order.toString().equals("ASC")) {
                return movieSerieService.findMovieSeriesByOrderByDateCreationAsc().stream()
                        .map(this::convertToMovieSerieDTO)
                        .collect(Collectors.toList());
            }
            if (order.toString().equals("DESC")) {
                return movieSerieService.findMovieSeriesByOrderByDateCreationDesc().stream()
                        .map(this::convertToMovieSerieDTO)
                        .collect(Collectors.toList());
            }
        }
        return null;
    }


    @Override
    public MovieSerie update(Long idMovieSerie, MovieSerie movieSerie) {
        log.info("Updating MovieSerie with id " + idMovieSerie);
        MovieSerie updateMovie = movieSerieService.findById(idMovieSerie).get();
        updateMovie.setIdMovieSerie(idMovieSerie);
        updateMovie.setTitle(movieSerie.getTitle());
        updateMovie.setRating(movieSerie.getRating());
        updateMovie.setDateCreation(movieSerie.getDateCreation());
        updateMovie.setImage(movieSerie.getImage());
        updateMovie.setGender(movieSerie.getGender());
        return movieSerieService.save(updateMovie);
    }

    @Override
    public MovieSerie addCharacter(Long idMovieSerie, Long idCharacter) {
        MovieSerie updateMovie = movieSerieService.findById(idMovieSerie).get();

        Collection<Character> character= updateMovie.getCharacter();
        Character characters= characterRepository.findById(idCharacter).get();
        AtomicBoolean exist= new AtomicBoolean(false);
        character.forEach(character1 -> {
            if (Objects.equals(character1.getIdCharacter(), characters.getIdCharacter())) {
                exist.set(true);
            }
        });
        if (!exist.get()) {
            character.add(characterRepository.findById(idCharacter).get());
        }
        return updateMovie;
    }

    @Override
    public MovieSerie removeCharacter(Long idMovieSerie, Long idCharacter) {
        MovieSerie updateMovie = movieSerieService.findById(idMovieSerie).get();
        if (updateMovie.getCharacter().size()>0) {
            Collection<Character> character= updateMovie.getCharacter();
            character.remove(characterRepository.findById(idCharacter).get());
        }
        return updateMovie;
    }

    @Override
    public boolean delete(Long idMovieSerie) {
        log.info("Deleting MovieSerie with id " + idMovieSerie);
        movieSerieService.deleteById(idMovieSerie);
        return true;
    }

    public MovieSerieDTO convertToMovieSerieDTO(MovieSerie movieSeries) {
        MovieSerieDTO movieSerieDTO = new MovieSerieDTO();
        movieSerieDTO.setTitle(movieSeries.getTitle());
        movieSerieDTO.setImage(movieSeries.getImage());
        movieSerieDTO.setDateCreation(movieSeries.getDateCreation());
        return movieSerieDTO;
    }
}
