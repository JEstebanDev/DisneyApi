package jestebancdev.DisneyApi.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Juan Esteban Castaño Holguin castanoesteban9@gmail.com 7/5/2022
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Gender {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(name = "idGender",example = "1")
    private Long idGender;
    @Schema(name = "name",example = "Aventura")
    @NotNull(message = "nombre no puede estar vacio")
    private String name;
    @Schema(name = "image",example = "image.url")
    private String image;
}

