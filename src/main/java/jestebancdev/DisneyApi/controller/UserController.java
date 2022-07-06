package jestebancdev.DisneyApi.controller;

import jestebancdev.DisneyApi.dto.LoginDTO;
import jestebancdev.DisneyApi.model.Response;
import jestebancdev.DisneyApi.model.UserApp;
import jestebancdev.DisneyApi.services.UserServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.Instant;
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

    @PostMapping("/login")
    public ResponseEntity<Response> loginUser(HttpServletRequest request,@RequestBody @Valid LoginDTO login) {
        if (serviceImp.findByUsername(login.getUsername()) != null) {
            return ResponseEntity.ok(Response.builder().timeStamp(Instant.now())
                    .data(Map.of("token", serviceImp.login(request,login))).message("Login user")
                    .status(HttpStatus.OK).statusCode(HttpStatus.OK.value()).build());

        } else {
            return ResponseEntity.ok(Response.builder().timeStamp(Instant.now())
                    .message("The username: " +login.getUsername() + " already exist")
                    .status(HttpStatus.BAD_REQUEST).statusCode(HttpStatus.BAD_REQUEST.value()).build());
        }
    }

}
