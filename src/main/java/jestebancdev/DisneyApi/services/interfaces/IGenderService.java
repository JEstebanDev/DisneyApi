package jestebancdev.DisneyApi.services.interfaces;

import jestebancdev.DisneyApi.model.Gender;

import java.util.Collection;

/**
 * @author Juan Esteban Castaño Holguin castanoesteban9@gmail.com 7/5/2022
 */
public interface IGenderService {

    Gender create(Gender gender);

    Collection<Gender> read();
    Gender update(Long idGender, Gender gender);

    boolean delete(Long idGender);
}
