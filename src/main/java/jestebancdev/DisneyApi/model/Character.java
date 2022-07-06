package jestebancdev.DisneyApi.model;

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
    private Long idCharacter;
    private String image;
    @NotNull(message = "nombre no puede estar vacio")
    private String name;
    @NotNull(message = "edad no puede estar vacio")
    private int age;
    @NotNull(message = "peso no puede estar vacio")
    private int weight;
    private String story;


}
