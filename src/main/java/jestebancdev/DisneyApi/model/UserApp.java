package jestebancdev.DisneyApi.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jestebancdev.DisneyApi.enumeration.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * @author Juan Esteban Castaño Holguin castanoesteban9@gmail.com 7/6/2022
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserApp {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(name = "idUser",example = "1")
    private Long idUser;
    @Schema(name = "username",example = "admin")
    @NotNull(message = "username no puede estar vacio")
    private String username;
    @Schema(name = "email",example = "example@gmail.com")
    @NotNull(message = "email no puede estar vacio")
    private String email;
    @Schema(name = "password",example = "123456")
    @NotNull(message = "password no puede estar vacio")
    private String password;
    @Schema(name = "role",example = "ROLE_ADMIN")
    private Role role;
}
