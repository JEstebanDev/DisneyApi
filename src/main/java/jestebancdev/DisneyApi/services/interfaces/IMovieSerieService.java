package jestebancdev.DisneyApi.services.interfaces;

import jestebancdev.DisneyApi.enumeration.Order;
import  jestebancdev.DisneyApi.model.MovieSerie;

import java.util.Collection;

/**
 * @author Juan Esteban Castaño Holguin castanoesteban9@gmail.com 7/5/2022
 */
public interface IMovieSerieService {

    MovieSerie create(MovieSerie movieSerie);

    Collection<MovieSerie> read(String title, Long idGender, Order order);

    MovieSerie update(Long idMovieSerie,MovieSerie movieSerie);

    boolean delete(Long idMovieSerie);
}
