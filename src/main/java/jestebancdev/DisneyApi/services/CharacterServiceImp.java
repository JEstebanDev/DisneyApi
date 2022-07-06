package jestebancdev.DisneyApi.services;

import jestebancdev.DisneyApi.model.Character;
import jestebancdev.DisneyApi.repository.ICharacterRepository;
import jestebancdev.DisneyApi.services.interfaces.ICharacterService;
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
public class CharacterServiceImp implements ICharacterService {

    @Autowired
    private ICharacterRepository characterRepository;

    @Override
    public Character create(Character character) {
        log.info("Creating Character");
        return characterRepository.save(character);
    }

    @Override
    public Collection<Character> read(String name, int age, int weight, Long idMovieSerie) {
        boolean withoutParameters = name == null && age <= 0 && weight <= 0 && idMovieSerie <= 0;
        boolean byName = name != null;
        boolean byAge = age >= 0;
        boolean byWeight = weight >= 0;
        boolean byIdMovieSerie = idMovieSerie >= 0;
        if (withoutParameters) {
            return characterRepository.findAll();
        }
        if (byName) {
            return characterRepository.findCharacterByNameStartingWith(name);
        }
        if (byAge) {
            return characterRepository.findAllByAge(age);
        }
        if (byWeight) {
            return characterRepository.findAllByWeight(weight);
        }
        return null;
    }

    @Override
    public Character update(Long idCharacter, Character character) {
        log.info("Updating Character with id " + idCharacter);
        Character updateCharacter = characterRepository.findById(idCharacter).get();
        updateCharacter.setIdCharacter(idCharacter);
        updateCharacter.setImage(character.getImage());
        updateCharacter.setWeight(character.getWeight());
        updateCharacter.setAge(character.getAge());
        updateCharacter.setName(character.getName());
        updateCharacter.setStory(character.getStory());
        return characterRepository.save(updateCharacter);

    }

    @Override
    public boolean delete(Long idCharacter) {
        log.info("Deleting Character with id " + idCharacter);
        characterRepository.deleteById(idCharacter);
        return true;
    }
}
