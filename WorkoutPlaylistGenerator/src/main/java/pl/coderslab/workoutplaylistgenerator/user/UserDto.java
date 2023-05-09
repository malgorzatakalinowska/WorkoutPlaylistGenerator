package pl.coderslab.workoutplaylistgenerator.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    @Schema(description = "User id", example = "2")
    private Long id;

    @Schema(description = "User nickname", example = "Malgorzata")
    private String displayName;

    @Schema(description = "User password", example = "coderslab", required = true)
    @NotBlank
    private String password;

    @Schema(description = "User email address", example = "malgorzatakalinowska100@wp.pl", required = true)
    @Email
    private String email;
}
