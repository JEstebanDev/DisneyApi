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
public class Gender {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idGender;
    @NotNull(message = "nombre no puede estar vacio")
    private String name;
    private String image;
    @ManyToOne
    @NotNull(message = "idPeliculaSerie no puede estar vacio")
    @JoinColumn(name = "idPeliculaSerie")
    private MovieSerie movieSerie;
}
