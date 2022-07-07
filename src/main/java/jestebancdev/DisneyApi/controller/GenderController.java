package jestebancdev.DisneyApi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jestebancdev.DisneyApi.model.Gender;
import jestebancdev.DisneyApi.model.Response;
import jestebancdev.DisneyApi.services.GenderServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/gender")
public class GenderController {
    @Autowired
    private final GenderServiceImp serviceImp;

    @Operation(summary = "Guarda genero")
    @PostMapping()
    public ResponseEntity<Response> saveGender(@RequestBody @Valid Gender gender) {

        return ResponseEntity.ok(Response.builder().timeStamp(Instant.now())
                .data(Map.of("gender", serviceImp.create(gender))).message("Created gender")
                .status(HttpStatus.OK).statusCode(HttpStatus.OK.value()).build());
    }

    @Operation(summary = "listar los generos")
    @GetMapping()
    public ResponseEntity<Response> readGenders() {
        Collection<Gender> read = serviceImp.read();
        if (read.size() > 0) {
            return ResponseEntity.ok(Response.builder().timeStamp(Instant.now())
                    .data(Map.of("list Genders", read)).message("Readed Genders")
                    .status(HttpStatus.OK).statusCode(HttpStatus.OK.value()).build());
        } else {
            return ResponseEntity.ok(Response.builder().timeStamp(Instant.now()).message("There are not genders")
                    .status(HttpStatus.OK).statusCode(HttpStatus.OK.value()).build());
        }
    }

    @Operation(summary = "Actualizar los generos")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Response> updateGender(@PathVariable("id") Long idGender, @RequestBody @Valid Gender Gender) {
        if (serviceImp.exist(idGender)) {
            return ResponseEntity.ok(Response.builder().timeStamp(Instant.now())
                    .data(Map.of("Gender", serviceImp.update(idGender, Gender))).message("Updated Gender")
                    .status(HttpStatus.OK).statusCode(HttpStatus.OK.value()).build());
        } else {
            return ResponseEntity.ok(Response.builder().timeStamp(Instant.now()).message("The gender with id " + idGender + " does not exist")
                    .status(HttpStatus.BAD_REQUEST).statusCode(HttpStatus.BAD_REQUEST.value()).build());
        }
    }
    @Operation(summary = "Eliminar generos de peliculas")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Response> deleteGender(@PathVariable("id") Long idGender) {
        if (serviceImp.exist(idGender)) {
            return ResponseEntity.ok(Response.builder().timeStamp(Instant.now())
                    .data(Map.of("Gender", serviceImp.delete(idGender))).message("Deleted Gender")
                    .status(HttpStatus.OK).statusCode(HttpStatus.OK.value()).build());
        } else {
            return ResponseEntity.ok(Response.builder().timeStamp(Instant.now()).message("The gender with id " + idGender + " does not exist")
                    .status(HttpStatus.BAD_REQUEST).statusCode(HttpStatus.BAD_REQUEST.value()).build());
        }

    }
}
