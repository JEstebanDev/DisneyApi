package jestebancdev.DisneyApi.dto;

import jestebancdev.DisneyApi.model.Character;
import lombok.Data;

import java.util.Collection;

/**
 * @author Juan Esteban Castaño Holguin castanoesteban9@gmail.com 7/6/2022
 */
@Data
public class CharacterMovieSerieDTO {

    Character characterInfo;
    Collection<MovieSerieCharacterDTO> movie_serie;
}
