package jestebancdev.DisneyApi.services;

import jestebancdev.DisneyApi.dto.CharacterMovieSerieDTO;
import jestebancdev.DisneyApi.model.Character;
import jestebancdev.DisneyApi.model.Gender;
import jestebancdev.DisneyApi.model.MovieSerie;
import jestebancdev.DisneyApi.repository.ICharacterRepository;
import jestebancdev.DisneyApi.repository.IMovieSerieRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author Juan Esteban Castaño Holguin castanoesteban9@gmail.com 7/7/2022
 */
@ExtendWith(MockitoExtension.class)
class MovieSerieServiceImpTest {
    @Mock
    private IMovieSerieRepository movieSerieRepository;
    @InjectMocks
    private MovieSerieServiceImp movieSerieServiceImp;
    private AutoCloseable autoCloseable;
    private MovieSerie movieSeries;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        movieSeries = new MovieSerie();
        movieSeries.setIdMovieSerie(1L);
        movieSeries.setTitle("Buzz lighyear");
        movieSeries.setImage("url.image");
        Gender gender=new Gender();
        gender.setIdGender(1L);
        gender.setName("Accion");
        gender.setImage("url.image");
        movieSeries.setGender(gender);
        movieSeries.setDateCreation(Date.from(Instant.now()));
        movieSeries.setRating(50);
        Character  character = new Character();
        character.setIdCharacter(1L);
        character.setName("Buzz lighyear");
        character.setImage("url.image");
        character.setAge(30);
        character.setWeight(80);
        character.setStory("Long Text");
        Collection<Character> listCharacter=new ArrayList<>();
        listCharacter.add(character);
        movieSeries.setCharacter(listCharacter);
    }

    @AfterEach
    void Destroy() throws Exception {
        autoCloseable.close();
    }
    @Test
    void create() {
        MovieSerie movieSerie2 = new MovieSerie();
        when(movieSerieRepository.save(any(MovieSerie.class))).thenReturn(movieSerie2);
        MovieSerie movieSerie3 = movieSerieServiceImp.create(movieSeries);
        assertNotNull(movieSerie3);
    }

    @Test
    void read() {
        List<MovieSerie> movieSeries=movieSerieRepository.findAll();
        assertNotNull(movieSeries);
    }

    @Test
    void delete() {
        movieSerieRepository.deleteById(1L);
        assertEquals(movieSerieRepository.findById(1L), Optional.empty());
    }
}