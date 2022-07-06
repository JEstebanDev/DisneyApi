package jestebancdev.DisneyApi.services.interfaces;

import jestebancdev.DisneyApi.model.UserApp;

import java.util.Collection;

/**
 * @author Juan Esteban Castaño Holguin castanoesteban9@gmail.com 7/6/2022
 */
public interface IUserService {

    UserApp create(UserApp user);

    Collection<UserApp> read();

    UserApp update(Long idUser, UserApp user);

    boolean delete(Long idUser);
}
