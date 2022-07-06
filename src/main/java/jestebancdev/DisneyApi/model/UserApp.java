package jestebancdev.DisneyApi.model;

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
    private Long idUser;
    @NotNull(message = "username no puede estar vacio")
    private String username;
    @NotNull(message = "email no puede estar vacio")
    private String email;
    @NotNull(message = "password no puede estar vacio")
    private String password;
    private Role role;
}
