package jestebancdev.DisneyApi.services;

import jestebancdev.DisneyApi.dto.CharacterMovieSerieDTO;
import jestebancdev.DisneyApi.model.Character;
import jestebancdev.DisneyApi.model.MovieSerie;
import jestebancdev.DisneyApi.repository.ICharacterRepository;
import jestebancdev.DisneyApi.repository.IMovieSerieRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author Juan Esteban Castaño Holguin castanoesteban9@gmail.com 7/7/2022
 */
@ExtendWith(MockitoExtension.class)
class CharacterServiceImpTest {

    @Mock
    private ICharacterRepository characterRepository;
    @Mock
    private IMovieSerieRepository movieSerieRepository;
    @InjectMocks
    private CharacterServiceImp characterServiceImp;
    private AutoCloseable autoCloseable;
    private Character character;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        character = new Character();
        character.setIdCharacter(1L);
        character.setName("Buzz lighyear");
        character.setImage("url.image");
        character.setAge(30);
        character.setWeight(80);
        character.setStory("Long Text");
    }

    @AfterEach
    void Destroy() throws Exception {
        autoCloseable.close();
    }

    @Test
    void create() {
        Character character2 = new Character();
        when(characterRepository.save(any(Character.class))).thenReturn(character2);
        CharacterMovieSerieDTO character3 = characterServiceImp.create(character);
        assertNotNull(character3);
        assertSame(character3.getCharacterInfo().getIdCharacter(), character2.getIdCharacter());
        assertSame(character3.getCharacterInfo().getStory(), character2.getStory());
        assertSame(character3.getCharacterInfo().getImage(), character2.getImage());
        assertSame(character3.getCharacterInfo().getName(), character2.getName());
        assertSame(character3.getCharacterInfo().getAge(), character2.getAge());
        assertSame(character3.getCharacterInfo().getWeight(), character2.getWeight());
    }


    @Test
    void detailCharacter() {
        List<Character> character = characterRepository.findAll();
        Assertions.assertNotNull(character);
    }

    @Test
    void read() {
        Collection<Character> character1 = characterRepository.findCharacterByNameStartingWith("Buzz");
        Collection<Character> character2 = characterRepository.findAllByAge(30);
        Collection<Character> character3 = characterRepository.findAllByWeight(80);
        Collection<Character> character4 = characterRepository.findByIdMovieSerie(1L);
        checkValues(character1);
        checkValues(character2);
        checkValues(character3);
        checkValues(character4);
        Assertions.assertNotNull(character);
    }

    void checkValues(Collection<Character> character) {
        character.forEach(element -> {
            Assertions.assertNotEquals(0, element.getIdCharacter());
            Assertions.assertNotEquals("", element.getName());
            Assertions.assertNotEquals(0, element.getWeight());
            Assertions.assertNotEquals(0, element.getAge());
            Assertions.assertNotEquals("", element.getStory());
            Assertions.assertNotEquals("", element.getImage());
        });
    }

    @Test
    void delete() {
        characterRepository.deleteById(1L);
        assertEquals(characterRepository.findById(1L), Optional.empty());
    }

    @Test
    void convertCharacterMovieSerieDTO() {
        Collection<MovieSerie> movieSerie = movieSerieRepository.searchByIdCharacter(1L);
        Assertions.assertNotNull(movieSerie);
    }
}