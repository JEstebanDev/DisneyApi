package jestebancdev.DisneyApi.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jestebancdev.DisneyApi.enumeration.Order;
import jestebancdev.DisneyApi.model.MovieSerie;
import jestebancdev.DisneyApi.model.Response;
import jestebancdev.DisneyApi.services.MovieSerieServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/movies")
public class MovieSerieController {

    @Autowired
    private final MovieSerieServiceImp serviceImp;
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping()
    public ResponseEntity<Response> saveMovieSerie(@RequestBody @Valid MovieSerie movieSerie) {

        return ResponseEntity.ok(Response.builder().timeStamp(Instant.now())
                .data(Map.of("movieSerie", serviceImp.create(movieSerie))).message("Creating MovieSerie")
                .status(HttpStatus.OK).statusCode(HttpStatus.OK.value()).build());


    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping()
    public ResponseEntity<Response> readMovieSeries(@RequestParam(value = "name",defaultValue = "")  String title,
                                                    @RequestParam(value = "genre",defaultValue="0") Long idGender,
                                                    @RequestParam(value = "order") Order order) {

        return ResponseEntity.ok(Response.builder().timeStamp(Instant.now())
                .data(Map.of("list movieSeries", serviceImp.read(title, idGender, order))).message("Read MovieSeries")
                .status(HttpStatus.OK).statusCode(HttpStatus.OK.value()).build());

    }

    @PutMapping(value = "/{idMovieSerie}")
    public ResponseEntity<Response> updateMovieSerie(@PathVariable("idMovieSerie") Long idMovieSerie, @RequestBody @Valid MovieSerie movieSerie) {

        return ResponseEntity.ok(Response.builder().timeStamp(Instant.now())
                .data(Map.of("movieSerie", serviceImp.update(idMovieSerie, movieSerie))).message("Updated MovieSerie")
                .status(HttpStatus.OK).statusCode(HttpStatus.OK.value()).build());

    }
}
