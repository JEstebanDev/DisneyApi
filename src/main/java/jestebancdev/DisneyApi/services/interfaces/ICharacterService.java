package jestebancdev.DisneyApi.services.interfaces;

import jestebancdev.DisneyApi.dto.CharacterDTO;
import jestebancdev.DisneyApi.dto.CharacterMovieSerieDTO;
import jestebancdev.DisneyApi.model.Character;

import java.util.Collection;

/**
 * @author Juan Esteban Castaño Holguin castanoesteban9@gmail.com 7/5/2022
 */
public interface ICharacterService {

    CharacterMovieSerieDTO create(Character character);

    Collection<CharacterMovieSerieDTO> detailCharacter();
    Collection<CharacterDTO> read(String name, int age, int weight, Long idMovieSerie);

    CharacterMovieSerieDTO update(Long idCharacter, Character character);

    boolean delete(Long idCharacter);
}
