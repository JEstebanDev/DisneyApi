package jestebancdev.DisneyApi.repository;

import jestebancdev.DisneyApi.model.MovieSerie;
import jestebancdev.DisneyApi.model.MovieSerie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;


/**
 * @author Juan Esteban Castaño Holguin castanoesteban9@gmail.com 7/5/2022
 */
@Repository
public interface IMovieSerieRepository extends JpaRepository<MovieSerie, Long> {

    @Query(value = "select Movie.* from movie_serie Movie join gender Gender on Movie.id_movie_serie=Gender.id_movie_serie " +
            "where Movie.titulo like :titulo'%' and Gender.id_gender=:IdGender order by Movie.titulo :order", nativeQuery = true)
    Collection<MovieSerie> allParameters(@Param("titulo") String titulo, @Param("IdGender") Long IdGender, @Param("order") String order);

    Collection<MovieSerie> findMovieSerieByTitleStartingWith(String titulo);

    @Query(value = "select Movie.* from movie_serie Movie join gender Gender on Movie.id_movie_serie=Gender.id_movie_serie " +
            "where Gender.id_gender=:IdGender", nativeQuery = true)
    Collection<MovieSerie> searchByIdGender(@Param("IdGender") Long IdGender);


    @Query(value = "select * from movie_serie order by titulo :order", nativeQuery = true)
    Collection<MovieSerie> searchByOrder(@Param("order") String order);

}
