package jestebancdev.DisneyApi.repository;

import jestebancdev.DisneyApi.model.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Juan Esteban Castaño Holguin castanoesteban9@gmail.com 7/5/2022
 */
@Repository
public interface IGenderRepository extends JpaRepository<Gender,Long> {


}
