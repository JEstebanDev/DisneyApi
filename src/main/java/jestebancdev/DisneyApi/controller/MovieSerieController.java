package jestebancdev.DisneyApi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jestebancdev.DisneyApi.dto.MovieSerieDTO;
import jestebancdev.DisneyApi.enumeration.Order;
import jestebancdev.DisneyApi.model.MovieSerie;
import jestebancdev.DisneyApi.model.Response;
import jestebancdev.DisneyApi.services.MovieSerieServiceImp;
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
@RequestMapping("/movies")
public class MovieSerieController {

    @Autowired
    private final MovieSerieServiceImp serviceImp;

    @Operation(summary = "Guardar peliculas")
    @PostMapping()
    public ResponseEntity<Response> saveMovieSerie(@RequestBody @Valid MovieSerie movieSerie) {

        return ResponseEntity.ok(Response.builder().timeStamp(Instant.now())
                .data(Map.of("movieSerie", serviceImp.create(movieSerie))).message("Creating MovieSerie")
                .status(HttpStatus.OK).statusCode(HttpStatus.OK.value()).build());


    }

    @Operation(summary = "Listar peliculas")
    @GetMapping()
    public ResponseEntity<Response> readMovieSeries(@RequestParam(value = "name", defaultValue = "") String title,
                                                    @RequestParam(value = "genre", defaultValue = "0") Long idGender,
                                                    @RequestParam(value = "order") @Nullable Order order) {
        Collection<MovieSerieDTO> read = serviceImp.read(title, idGender, order);
        if (read.size() > 0) {
            return ResponseEntity.ok(Response.builder().timeStamp(Instant.now())
                    .data(Map.of("list movieSeries", serviceImp.read(title, idGender, order))).message("Read MovieSeries")
                    .status(HttpStatus.OK).statusCode(HttpStatus.OK.value()).build());

        } else {
            return ResponseEntity.ok(Response.builder().timeStamp(Instant.now()).message("There are not MovieSeries")
                    .status(HttpStatus.OK).statusCode(HttpStatus.OK.value()).build());
        }

    }

    @Operation(summary = "Listar peliculas con todos los parametros")
    @GetMapping(value = "/detail")
    public ResponseEntity<Response> detailMovieSeries() {
        return ResponseEntity.ok(Response.builder().timeStamp(Instant.now())
                .data(Map.of("details movieSeries", serviceImp.detailMovieSerie())).message("Detail MovieSeries")
                .status(HttpStatus.OK).statusCode(HttpStatus.OK.value()).build());

    }

    @Operation(summary = "Actualizar peliculas")
    @PutMapping(value = "/{idMovieSerie}")
    public ResponseEntity<Response> updateMovieSerie(@PathVariable("idMovieSerie") Long idMovieSerie, @RequestBody @Valid MovieSerie movieSerie) {
        if (serviceImp.exist(idMovieSerie)) {
            return ResponseEntity.ok(Response.builder().timeStamp(Instant.now())
                    .data(Map.of("movieSerie", serviceImp.update(idMovieSerie, movieSerie))).message("Updated MovieSerie")
                    .status(HttpStatus.OK).statusCode(HttpStatus.OK.value()).build());
        } else {
            return ResponseEntity.ok(Response.builder().timeStamp(Instant.now()).message("The MovieSerie with id " + idMovieSerie + " does not exist")
                    .status(HttpStatus.BAD_REQUEST).statusCode(HttpStatus.BAD_REQUEST.value()).build());
        }
    }

    @Operation(summary = "Añadir personajes")
    @PostMapping(value = "/{idMovieSerie}/character/{idCharacter}")
    public ResponseEntity<Response> addCharacter(@PathVariable("idMovieSerie") Long idMovieSerie, @PathVariable("idCharacter") Long idCharacter) {
        if (serviceImp.exist(idMovieSerie)) {
            return ResponseEntity.ok(Response.builder().timeStamp(Instant.now())
                    .data(Map.of("movieSerie", serviceImp.addCharacter(idMovieSerie, idCharacter))).message("Updated Add Character")
                    .status(HttpStatus.OK).statusCode(HttpStatus.OK.value()).build());
        } else {
            return ResponseEntity.ok(Response.builder().timeStamp(Instant.now()).message("The MovieSerie with id " + idMovieSerie + " does not exist")
                    .status(HttpStatus.BAD_REQUEST).statusCode(HttpStatus.BAD_REQUEST.value()).build());
        }
    }

    @Operation(summary = "Remover personajes")
    @DeleteMapping(value = "/{idMovieSerie}/character/{idCharacter}")
    public ResponseEntity<Response> deleteCharacter(@PathVariable("idMovieSerie") Long idMovieSerie, @PathVariable("idCharacter") Long idCharacter) {
        if (serviceImp.exist(idMovieSerie)) {
            return ResponseEntity.ok(Response.builder().timeStamp(Instant.now())
                    .data(Map.of("movieSerie", serviceImp.removeCharacter(idMovieSerie, idCharacter))).message("Updated Remove Character")
                    .status(HttpStatus.OK).statusCode(HttpStatus.OK.value()).build());
        } else {
            return ResponseEntity.ok(Response.builder().timeStamp(Instant.now()).message("The MovieSerie with id " + idMovieSerie + " does not exist")
                    .status(HttpStatus.BAD_REQUEST).statusCode(HttpStatus.BAD_REQUEST.value()).build());
        }
    }

    @Operation(summary = "Eliminar pelicula")
    @DeleteMapping(value = "/{idMovieSerie}")
    public ResponseEntity<Response> deleteMovieSerie(@PathVariable("idMovieSerie") Long idMovieSerie) {
        if (serviceImp.exist(idMovieSerie)) {
            return ResponseEntity.ok(Response.builder().timeStamp(Instant.now())
                    .data(Map.of("movieSerie", serviceImp.delete(idMovieSerie))).message("Deleted MovieSerie")
                    .status(HttpStatus.OK).statusCode(HttpStatus.OK.value()).build());
        } else {
            return ResponseEntity.ok(Response.builder().timeStamp(Instant.now()).message("The MovieSerie with id " + idMovieSerie + " does not exist")
                    .status(HttpStatus.BAD_REQUEST).statusCode(HttpStatus.BAD_REQUEST.value()).build());
        }
    }
}
