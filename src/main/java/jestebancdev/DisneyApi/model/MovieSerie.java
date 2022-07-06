package jestebancdev.DisneyApi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * @author Juan Esteban Castaño Holguin castanoesteban9@gmail.com 7/5/2022
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieSerie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idMovieSerie;
    private String image;
    @NotNull(message = "titulo no puede estar vacio")
    private String title;
    @NotNull(message = "fechaCreacion no puede estar vacio")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    private Date dateCreation;
    @NotNull(message = "La calificación no puede estar vacia")
    private int rating;

    @ManyToMany
    @JoinColumn(name = "idCharacter")
    @JoinTable(joinColumns = @JoinColumn(name = "idMovieSerie"),
            inverseJoinColumns = @JoinColumn(name = "idCharacter"))
    private Collection<Character> character = new ArrayList<>();

}
