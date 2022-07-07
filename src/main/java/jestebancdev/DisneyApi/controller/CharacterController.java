package jestebancdev.DisneyApi.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jestebancdev.DisneyApi.model.Character;
import jestebancdev.DisneyApi.model.Response;
import jestebancdev.DisneyApi.services.CharacterServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Instant;
import java.util.Map;

/**
 * @author Juan Esteban Castaño Holguin castanoesteban9@gmail.com 7/6/2022
 */
@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "disney-api")
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/characters")
public class CharacterController {
    @Autowired
    private final CharacterServiceImp serviceImp;

    @PostMapping()
    public ResponseEntity<Response> saveCharacter(@RequestBody @Valid Character character) {

        return ResponseEntity.ok(Response.builder().timeStamp(Instant.now())
                .data(Map.of("character", serviceImp.create(character))).message("Created character")
                .status(HttpStatus.OK).statusCode(HttpStatus.OK.value()).build());
    }

    @GetMapping(value = "/detail")
    public ResponseEntity<Response> detailCharacter(){
        return ResponseEntity.ok(Response.builder().timeStamp(Instant.now())
                .data(Map.of("list full characters", serviceImp.detailCharacter())).message("detail characters")
                .status(HttpStatus.OK).statusCode(HttpStatus.OK.value()).build());
    }

    @GetMapping()
    public ResponseEntity<Response> readCharacters(@RequestParam(value = "name",defaultValue = "") @Nullable String name,
                                                   @RequestParam(value = "age",defaultValue = "0") int age,
                                                   @RequestParam(value = "weight",defaultValue = "0") int weight,
                                                   @RequestParam(value = "movies",defaultValue = "0") Long idMovieSerie) {
        return ResponseEntity.ok(Response.builder().timeStamp(Instant.now())
                .data(Map.of("list characters", serviceImp.read(name, age, weight, idMovieSerie))).message("Read characters")
                .status(HttpStatus.OK).statusCode(HttpStatus.OK.value()).build());
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Response> updateCharacter(@PathVariable("id") Long idCharacter, @RequestBody @Valid Character character) {

        return ResponseEntity.ok(Response.builder().timeStamp(Instant.now())
                .data(Map.of("character", serviceImp.update(idCharacter, character))).message("Updated character")
                .status(HttpStatus.OK).statusCode(HttpStatus.OK.value()).build());


    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Response> deleteCharacter(@PathVariable("id") Long idCharacter) {

        return ResponseEntity.ok(Response.builder().timeStamp(Instant.now())
                .data(Map.of("character", serviceImp.delete(idCharacter))).message("Deleted character")
                .status(HttpStatus.OK).statusCode(HttpStatus.OK.value()).build());


    }
}
