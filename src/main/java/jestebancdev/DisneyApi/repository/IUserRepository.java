package jestebancdev.DisneyApi.repository;

import jestebancdev.DisneyApi.model.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Juan Esteban Castaño Holguin castanoesteban9@gmail.com 7/6/2022
 */
@Repository
public interface IUserRepository extends JpaRepository<UserApp, Long> {
    UserApp findByUsername(String username);
}
