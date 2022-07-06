package jestebancdev.DisneyApi.services.interfaces;

import jestebancdev.DisneyApi.model.Character;

import java.util.Collection;

/**
 * @author Juan Esteban Castaño Holguin castanoesteban9@gmail.com 7/5/2022
 */
public interface ICharacterService {

    Character create(Character character);

    Collection<Character> read(String name, int age, int weight, Long idMovieSerie);

    Character update(Long idCharacter, Character character);

    boolean delete(Long idCharacter);
}
