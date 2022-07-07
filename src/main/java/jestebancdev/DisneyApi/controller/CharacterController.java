package jestebancdev.DisneyApi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jestebancdev.DisneyApi.dto.CharacterDTO;
import jestebancdev.DisneyApi.dto.CharacterMovieSerieDTO;
import jestebancdev.DisneyApi.model.Character;
import jestebancdev.DisneyApi.model.Response;
import jestebancdev.DisneyApi.services.CharacterServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Instant;
import java.util.Collection;
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

    @Operation(summary = "Guarda un nuevo personaje")
    @ApiResponse(responseCode = "200", description = "Correcto")
    @PostMapping()
    public ResponseEntity<Response> saveCharacter(@RequestBody @Valid Character character) {

        return ResponseEntity.ok(Response.builder().timeStamp(Instant.now())
                .data(Map.of("character", serviceImp.create(character))).message("Created character")
                .status(HttpStatus.OK).statusCode(HttpStatus.OK.value()).build());
    }

    @Operation(summary = "Lista los personajes con todos sus campos")
    @GetMapping(value = "/detail")
    public ResponseEntity<Response> detailCharacter() {
        Collection<CharacterMovieSerieDTO> detailCharacter = serviceImp.detailCharacter();
        if (detailCharacter.size() > 0) {
            return ResponseEntity.ok(Response.builder().timeStamp(Instant.now())
                    .data(Map.of("list full characters", detailCharacter)).message("detail characters")
                    .status(HttpStatus.OK).statusCode(HttpStatus.OK.value()).build());
        } else {
            return ResponseEntity.ok(Response.builder().timeStamp(Instant.now()).message("There are not characters")
                    .status(HttpStatus.OK).statusCode(HttpStatus.OK.value()).build());
        }

    }
    @Operation(summary = "Lista los personajes")
    @GetMapping()
    public ResponseEntity<Response> readCharacters(@RequestParam(value = "name", defaultValue = "") @Nullable String name,
                                                   @RequestParam(value = "age", defaultValue = "0") int age,
                                                   @RequestParam(value = "weight", defaultValue = "0") int weight,
                                                   @RequestParam(value = "movies", defaultValue = "0") Long idMovieSerie) {
        Collection<CharacterDTO> read = serviceImp.read(name, age, weight, idMovieSerie);
        if (read.size() > 0) {
            return ResponseEntity.ok(Response.builder().timeStamp(Instant.now())
                    .data(Map.of("list characters", read)).message("Read characters")
                    .status(HttpStatus.OK).statusCode(HttpStatus.OK.value()).build());
        } else {
            return ResponseEntity.ok(Response.builder().timeStamp(Instant.now()).message("There are not characters")
                    .status(HttpStatus.OK).statusCode(HttpStatus.OK.value()).build());
        }
    }

    @Operation(summary = "Actualiza un personaje")
    @ApiResponse(responseCode = "200", description = "Correcto")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Response> updateCharacter(@PathVariable("id") Long idCharacter, @RequestBody @Valid Character character) {
        if (serviceImp.exist(idCharacter)) {
            return ResponseEntity.ok(Response.builder().timeStamp(Instant.now())
                    .data(Map.of("character", serviceImp.update(idCharacter, character))).message("Updated character")
                    .status(HttpStatus.OK).statusCode(HttpStatus.OK.value()).build());
        } else {
            return ResponseEntity.ok(Response.builder().timeStamp(Instant.now()).message("The character does not exist")
                    .status(HttpStatus.BAD_REQUEST).statusCode(HttpStatus.BAD_REQUEST.value()).build());
        }
    }
    @Operation(summary = "Elimina un personaje")
    @ApiResponse(responseCode = "200", description = "Correcto")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Response> deleteCharacter(@PathVariable("id") Long idCharacter) {
        if (serviceImp.exist(idCharacter)) {
            return ResponseEntity.ok(Response.builder().timeStamp(Instant.now())
                    .data(Map.of("character", serviceImp.delete(idCharacter))).message("Deleted character")
                    .status(HttpStatus.OK).statusCode(HttpStatus.OK.value()).build());
        } else {
            return ResponseEntity.ok(Response.builder().timeStamp(Instant.now()).message("The character does not exist")
                    .status(HttpStatus.OK).statusCode(HttpStatus.OK.value()).build());
        }

    }
}
