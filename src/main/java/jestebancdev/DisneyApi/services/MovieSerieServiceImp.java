package jestebancdev.DisneyApi.services;

import jestebancdev.DisneyApi.enumeration.Order;
import jestebancdev.DisneyApi.model.MovieSerie;
import jestebancdev.DisneyApi.repository.IMovieSerieRepository;
import jestebancdev.DisneyApi.services.interfaces.IMovieSerieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

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

    @Override
    public MovieSerie create(MovieSerie movieSerie) {
        log.info("Create MovieSerie");
        return movieSerieService.save(movieSerie);
    }

    @Override
    public Collection<MovieSerie> read(String title, Long idGender, Order order) {
        log.info("Searching by Parameters MovieSerie");
        boolean withoutParameters= title == null && idGender <= 0 && order == null;
        boolean byTitle = title != null;
        boolean byIdGender = idGender > 0;
        boolean Order = order != null;
        if (withoutParameters) {
            return movieSerieService.findAll();
        }
        if (byTitle) {
            return movieSerieService.findMovieSerieByTitleStartingWith(title);
        }
        if (byIdGender) {
            return movieSerieService.searchByIdGender(idGender);
        }
        if (Order) {
            return movieSerieService.searchByOrder(order.toString());
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
        return movieSerieService.save(updateMovie);
    }

    @Override
    public boolean delete(Long idMovieSerie) {
        log.info("Eliminado peliculaSerie con id " + idMovieSerie);
        movieSerieService.deleteById(idMovieSerie);
        return true;
    }
}
