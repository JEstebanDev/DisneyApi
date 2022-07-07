package jestebancdev.DisneyApi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jestebancdev.DisneyApi.dto.LoginDTO;
import jestebancdev.DisneyApi.model.Response;
import jestebancdev.DisneyApi.model.UserApp;
import jestebancdev.DisneyApi.services.UserServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.Instant;
import java.util.Collection;
import java.util.Map;

/**
 * @author Juan Esteban Castaño Holguin castanoesteban9@gmail.com 7/6/2022
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private final UserServiceImp serviceImp;

    //	CREATE
    @Operation(summary = "Crear usuario")
    @PostMapping("/register")
    public ResponseEntity<Response> saveUser(@RequestBody @Valid UserApp user) {

        if (serviceImp.findByUsername(user.getUsername()) == null) {
            return ResponseEntity.ok(Response.builder().timeStamp(Instant.now())
                    .data(Map.of("user", serviceImp.create(user))).message("Create user")
                    .status(HttpStatus.OK).statusCode(HttpStatus.OK.value()).build());

        } else {
            return ResponseEntity.ok(Response.builder().timeStamp(Instant.now())
                    .message("The username: " + user.getUsername() + " already exist")
                    .status(HttpStatus.BAD_REQUEST).statusCode(HttpStatus.BAD_REQUEST.value()).build());
        }
    }

    //LOGIN
    @Operation(summary = "Login usuario")
    @PostMapping("/login")
    public ResponseEntity<Response> loginUser(HttpServletRequest request, @RequestBody @Valid LoginDTO login) {
        if (serviceImp.findByUsername(login.getUsername()) != null) {
            return ResponseEntity.ok(Response.builder().timeStamp(Instant.now())
                    .data(Map.of("token", serviceImp.login(request, login))).message("Login user")
                    .status(HttpStatus.OK).statusCode(HttpStatus.OK.value()).build());

        } else {
            return ResponseEntity.ok(Response.builder().timeStamp(Instant.now())
                    .message("The username: " + login.getUsername() + " already exist")
                    .status(HttpStatus.BAD_REQUEST).statusCode(HttpStatus.BAD_REQUEST.value()).build());
        }
    }

    @SecurityRequirement(name = "disney-api")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Listar usuarios")
    @GetMapping()
    public ResponseEntity<Response> read() {

        Collection<UserApp> read = serviceImp.read();
        if (read.size() > 0) {
            return ResponseEntity.ok(Response.builder().timeStamp(Instant.now())
                    .data(Map.of("user",read)).message("List users")
                    .status(HttpStatus.OK).statusCode(HttpStatus.OK.value()).build());

        } else {
            return ResponseEntity.ok(Response.builder().timeStamp(Instant.now())
                    .message("Not users found")
                    .status(HttpStatus.OK).statusCode(HttpStatus.OK.value()).build());
        }
    }

    @SecurityRequirement(name = "disney-api")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Actualizar usuarios")
    @PutMapping(value = "/{idUser}")
    public ResponseEntity<Response> updateUser(@PathVariable("idUser") Long idUser, @RequestBody @Valid UserApp user) {
        if (serviceImp.exist(idUser)) {
            return ResponseEntity.ok(Response.builder().timeStamp(Instant.now())
                    .data(Map.of("user", serviceImp.update(idUser, user))).message("Updated user")
                    .status(HttpStatus.OK).statusCode(HttpStatus.OK.value()).build());
        } else {
            return ResponseEntity.ok(Response.builder().timeStamp(Instant.now()).message("The user with id " + idUser + " does not exist")
                    .status(HttpStatus.BAD_REQUEST).statusCode(HttpStatus.BAD_REQUEST.value()).build());
        }
    }
    @SecurityRequirement(name = "disney-api")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Eliminar usuarios")
    @DeleteMapping(value = "/{idUser}")
    public ResponseEntity<Response> updateUser(@PathVariable("idUser") Long idUser) {
        if (serviceImp.exist(idUser)) {
            return ResponseEntity.ok(Response.builder().timeStamp(Instant.now())
                    .data(Map.of("user", serviceImp.delete(idUser))).message("Deleted user")
                    .status(HttpStatus.OK).statusCode(HttpStatus.OK.value()).build());
        } else {
            return ResponseEntity.ok(Response.builder().timeStamp(Instant.now()).message("The user with id " + idUser + " does not exist")
                    .status(HttpStatus.BAD_REQUEST).statusCode(HttpStatus.BAD_REQUEST.value()).build());
        }
    }

}
