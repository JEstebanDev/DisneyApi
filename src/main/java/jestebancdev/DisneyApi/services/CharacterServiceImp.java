package jestebancdev.DisneyApi.services;

import jestebancdev.DisneyApi.dto.CharacterDTO;
import jestebancdev.DisneyApi.dto.CharacterMovieSerieDTO;
import jestebancdev.DisneyApi.dto.MovieSerieCharacterDTO;
import jestebancdev.DisneyApi.model.Character;
import jestebancdev.DisneyApi.model.MovieSerie;
import jestebancdev.DisneyApi.repository.ICharacterRepository;
import jestebancdev.DisneyApi.repository.IMovieSerieRepository;
import jestebancdev.DisneyApi.services.interfaces.ICharacterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.stream.Collectors;

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
    @Autowired
    private IMovieSerieRepository movieSerieRepository;

    @Override
    public CharacterMovieSerieDTO create(Character character) {
        log.info("Creating Character");
        return convertCharacterMovieSerieDTO(characterRepository.save(character));
    }

    @Override
    public Collection<CharacterMovieSerieDTO> detailCharacter() {
        log.info("Reading with all information");
        return characterRepository.findAll().stream()
                .map(this::convertCharacterMovieSerieDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<CharacterDTO> read(String name, int age, int weight, Long idMovieSerie) {
        log.info("Reading Characters");
        boolean byName = !name.equals("");
        boolean byAge = age > 0;
        boolean byWeight = weight > 0;
        boolean byIdMovieSerie = idMovieSerie > 0;
        if (byName) {
            log.info("byName");
            return characterRepository.findCharacterByNameStartingWith(name).stream()
                    .map(this::convertCharacterDTO)
                    .collect(Collectors.toList());
        }
        if (byAge) {
            log.info("byAge");
            return characterRepository.findAllByAge(age).stream()
                    .map(this::convertCharacterDTO)
                    .collect(Collectors.toList());
        }
        if (byWeight) {
            log.info("byWeight");
            return characterRepository.findAllByWeight(weight).stream()
                    .map(this::convertCharacterDTO)
                    .collect(Collectors.toList());
        }
        if (byIdMovieSerie) {
            log.info("byIdMovieSerie");
            return characterRepository.findByIdMovieSerie(idMovieSerie).stream()
                    .map(this::convertCharacterDTO)
                    .collect(Collectors.toList());
        }
        log.info("withoutParameters");
        return characterRepository.findAll().stream()
                .map(this::convertCharacterDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CharacterMovieSerieDTO update(Long idCharacter, Character character) {
        log.info("Updating Character with id " + idCharacter);
        Character updateCharacter = characterRepository.findById(idCharacter).get();
        updateCharacter.setIdCharacter(idCharacter);
        updateCharacter.setImage(character.getImage());
        updateCharacter.setWeight(character.getWeight());
        updateCharacter.setAge(character.getAge());
        updateCharacter.setName(character.getName());
        updateCharacter.setStory(character.getStory());
        return convertCharacterMovieSerieDTO(characterRepository.save(updateCharacter));
    }

    @Override
    public boolean delete(Long idCharacter) {
        log.info("Deleting Character with id " + idCharacter);
        characterRepository.deleteById(idCharacter);
        return true;
    }

    @Override
    public boolean exist(Long idCharacter) {
        log.info("Searching Character with id " + idCharacter);
        return characterRepository.existsById(idCharacter);
    }

    public CharacterMovieSerieDTO convertCharacterMovieSerieDTO(Character character) {
        CharacterMovieSerieDTO searchByIdCharacter = new CharacterMovieSerieDTO();
        searchByIdCharacter.setCharacterInfo(character);
        Collection<MovieSerieCharacterDTO> movieSerieCharacterDTO = movieSerieRepository.searchByIdCharacter(character.getIdCharacter()).stream()
                .map(this::convertMovieSerieCharacterDTO)
                .collect(Collectors.toList());
        searchByIdCharacter.setMovie_serie(movieSerieCharacterDTO);
        return searchByIdCharacter;
    }

    public MovieSerieCharacterDTO convertMovieSerieCharacterDTO(MovieSerie movieSeries) {
        MovieSerieCharacterDTO movieSerieCharacterDTO = new MovieSerieCharacterDTO();
        movieSerieCharacterDTO.setIdMovieSerie(movieSeries.getIdMovieSerie());
        movieSerieCharacterDTO.setTitle(movieSeries.getTitle());
        movieSerieCharacterDTO.setRating(movieSeries.getRating());
        movieSerieCharacterDTO.setDateCreation(movieSeries.getDateCreation());
        movieSerieCharacterDTO.setImage(movieSeries.getImage());
        return movieSerieCharacterDTO;
    }

    public CharacterDTO convertCharacterDTO(Character character) {
        CharacterDTO characterDTO = new CharacterDTO();
        characterDTO.setName(character.getName());
        characterDTO.setImage(character.getImage());
        return characterDTO;
    }

}
