package jestebancdev.DisneyApi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author Juan Esteban Castaño Holguin castanoesteban9@gmail.com 7/6/2022
 */
@Data
public class LoginDTO {
    @Schema(name = "username",example = "admin")
    private String username;
    @Schema(name = "password",example = "123456")
    private String password;
}
