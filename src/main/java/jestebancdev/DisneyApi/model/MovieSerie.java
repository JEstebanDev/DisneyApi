package jestebancdev.DisneyApi.model;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(name = "idMovieSerie",example = "1")
    private Long idMovieSerie;
    @Schema(name = "image",example = "image.url")
    private String image;
    @Schema(name = "title",example = "Encanto")
    @NotNull(message = "titulo no puede estar vacio")
    private String title;
    @Schema(name = "dateCreation",example = "2022-03-12")
    @NotNull(message = "fechaCreacion no puede estar vacio")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    private Date dateCreation;
    @Schema(name = "rating",example = "82")
    @NotNull(message = "La calificación no puede estar vacia")
    private int rating;

    @ManyToOne
    @NotNull(message = "idGender no puede estar vacio")
    @JoinColumn(name = "idGender")
    private Gender gender;

    @ManyToMany
    @JoinColumn(name = "idCharacter")
    @JoinTable(joinColumns = @JoinColumn(name = "idMovieSerie"),
            inverseJoinColumns = @JoinColumn(name = "idCharacter"))
    private Collection<Character> character = new ArrayList<>();

}
