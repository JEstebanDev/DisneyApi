package jestebancdev.DisneyApi.controller;

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

    @PostMapping()
    public ResponseEntity<Response> saveGender(@RequestBody @Valid Gender gender) {

        return ResponseEntity.ok(Response.builder().timeStamp(Instant.now())
                .data(Map.of("gender", serviceImp.create(gender))).message("Created gender")
                .status(HttpStatus.OK).statusCode(HttpStatus.OK.value()).build());
    }


    @GetMapping()
    public ResponseEntity<Response> readGenders() {
        return ResponseEntity.ok(Response.builder().timeStamp(Instant.now())
                .data(Map.of("list Genders", serviceImp.read())).message("Readed Genders")
                .status(HttpStatus.OK).statusCode(HttpStatus.OK.value()).build());
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Response> updateGender(@PathVariable("id") Long idGender, @RequestBody @Valid Gender Gender) {

        return ResponseEntity.ok(Response.builder().timeStamp(Instant.now())
                .data(Map.of("Gender", serviceImp.update(idGender, Gender))).message("Updated Gender")
                .status(HttpStatus.OK).statusCode(HttpStatus.OK.value()).build());


    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Response> deleteGender(@PathVariable("id") Long idGender) {

        return ResponseEntity.ok(Response.builder().timeStamp(Instant.now())
                .data(Map.of("Gender", serviceImp.delete(idGender))).message("Deleted Gender")
                .status(HttpStatus.OK).statusCode(HttpStatus.OK.value()).build());


    }
}
