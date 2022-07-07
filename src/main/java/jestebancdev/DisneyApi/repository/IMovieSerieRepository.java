package jestebancdev.DisneyApi.repository;

import jestebancdev.DisneyApi.model.MovieSerie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;


/**
 * @author Juan Esteban Castaño Holguin castanoesteban9@gmail.com 7/5/2022
 */
@Repository
public interface IMovieSerieRepository extends JpaRepository<MovieSerie, Long> {

    Collection<MovieSerie> findMovieSerieByTitleStartingWith(String titulo);

    @Query(value = "SELECT * FROM movie_serie WHERE id_gender=?",nativeQuery = true)
    Collection<MovieSerie> findMovieSerieByIdGender(Long IdGender);

    Collection<MovieSerie> findMovieSeriesByOrderByDateCreationAsc();

    Collection<MovieSerie> findMovieSeriesByOrderByDateCreationDesc();

    @Query(value = "SELECT movie_serie.* FROM movie_serie_character Movie_serie_character " +
            "JOIN movie_serie Movie_serie ON Movie_serie_character.id_movie_serie=Movie_serie.id_movie_serie " +
            "WHERE Movie_serie_character.id_character=?", nativeQuery = true)
    Collection<MovieSerie> searchByIdCharacter(Long idCharacter);
}
