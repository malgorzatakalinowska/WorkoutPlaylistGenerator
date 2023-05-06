package pl.coderslab.workoutplaylistgenerator.workout;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class WorkoutDto {

    //@Schema(description = "Workout id", example = "3")
    private Long id;

    //@Schema(description = "Type of workout", example = "Zumba", required = true)
    @NotBlank
    private String type;

   //@Schema(description = "Intensity of workout", example = "3", required = true)
    private int intensity;

    private long userId;
}
