package jestebancdev.DisneyApi.services.interfaces;

import jestebancdev.DisneyApi.dto.MovieSerieDTO;
import jestebancdev.DisneyApi.enumeration.Order;
import  jestebancdev.DisneyApi.model.MovieSerie;

import java.util.Collection;

/**
 * @author Juan Esteban Castaño Holguin castanoesteban9@gmail.com 7/5/2022
 */
public interface IMovieSerieService {

    MovieSerie create(MovieSerie movieSerie);

    Collection<MovieSerieDTO> read(String title, Long idGender, Order order);

    MovieSerie update(Long idMovieSerie,MovieSerie movieSerie);

    MovieSerie addCharacter(Long idMovieSerie,Long idCharacter);

    MovieSerie removeCharacter(Long idMovieSerie,Long idCharacter);

    boolean delete(Long idMovieSerie);
}
