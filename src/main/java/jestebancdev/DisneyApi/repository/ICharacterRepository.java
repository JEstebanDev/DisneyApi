package jestebancdev.DisneyApi.repository;

import jestebancdev.DisneyApi.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * @author Juan Esteban Castaño Holguin castanoesteban9@gmail.com 7/5/2022
 */
@Repository

public interface ICharacterRepository extends JpaRepository<Character, Long> {

    Collection<Character> findCharacterByNameStartingWith(String name);

    Collection<Character> findAllByAge(int age);

    Collection<Character> findAllByWeight(int weight);

    @Query(value = "SELECT character.* FROM movie_serie_character Movie_serie_character " +
            "JOIN character Character ON Character.id_character=Movie_serie_character.id_character " +
            "WHERE Movie_serie_character.id_movie_serie=?", nativeQuery = true)
    Collection<Character> findByIdMovieSerie(Long idMovieSerie);

}
