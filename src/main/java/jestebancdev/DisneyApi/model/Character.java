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
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(name = "idCharacter",example = "1")
    private Long idCharacter;
    @Schema(name = "image",example = "url.image.png")
    private String image;
    @Schema(name = "name",example = "Encanto")
    @NotNull(message = "nombre no puede estar vacio")
    private String name;
    @Schema(name = "age",example = "30")
    @NotNull(message = "edad no puede estar vacio")
    private int age;
    @Schema(name = "weight",example = "70")
    @NotNull(message = "peso no puede estar vacio")
    private int weight;
    @Schema(name = "story",example = "Esta es la historia del personaje")
    private String story;

}
