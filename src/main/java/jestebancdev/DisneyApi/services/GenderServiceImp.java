package jestebancdev.DisneyApi.services;

import jestebancdev.DisneyApi.model.Gender;
import jestebancdev.DisneyApi.repository.IGenderRepository;
import jestebancdev.DisneyApi.services.interfaces.IGenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author Juan Esteban Castaño Holguin castanoesteban9@gmail.com 7/5/2022
 */
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class GenderServiceImp implements IGenderService {

    @Autowired
    private IGenderRepository generoRepository;

    @Override
    public Gender create(Gender gender) {
        log.info("Creando genero");
        return generoRepository.save(gender);
    }

    @Override
    public Gender update(Long idGenero, Gender gender) {
        log.info("Actualizando genero");
        Gender actualizarGender;
        actualizarGender = gender;
        actualizarGender.setIdGender(idGenero);
        return generoRepository.save(actualizarGender);
    }

    @Override
    public boolean delete(Long idGenero) {
        log.info("Borrando genero");
        generoRepository.deleteById(idGenero);
        return true;
    }
}
