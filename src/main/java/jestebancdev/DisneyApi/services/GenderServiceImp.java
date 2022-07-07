package jestebancdev.DisneyApi.services;

import jestebancdev.DisneyApi.model.Gender;
import jestebancdev.DisneyApi.repository.IGenderRepository;
import jestebancdev.DisneyApi.services.interfaces.IGenderService;
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
public class GenderServiceImp implements IGenderService {

    @Autowired
    private IGenderRepository genderRepository;

    @Override
    public Gender create(Gender gender) {
        log.info("Creating gender");
        return genderRepository.save(gender);
    }

    @Override
    public Collection<Gender> read() {
        log.info("Reading genders");
        return genderRepository.findAll();
    }

    @Override
    public Gender update(Long idGender, Gender gender) {
        log.info("Updating gender");
        gender.setIdGender(idGender);
        return genderRepository.save(gender);
    }

    @Override
    public boolean delete(Long idGender) {
        log.info("Deleting gender");
        genderRepository.deleteById(idGender);
        return true;
    }
}
