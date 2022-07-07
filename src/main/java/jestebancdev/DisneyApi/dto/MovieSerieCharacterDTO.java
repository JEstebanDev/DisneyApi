package jestebancdev.DisneyApi.dto;

import lombok.Data;
import java.util.Date;

/**
 * @author Juan Esteban Castaño Holguin castanoesteban9@gmail.com 7/6/2022
 */
@Data
public class MovieSerieCharacterDTO {
    private Long idMovieSerie;
    private String image;
    private String title;
    private Date dateCreation;
    private int rating;
}
