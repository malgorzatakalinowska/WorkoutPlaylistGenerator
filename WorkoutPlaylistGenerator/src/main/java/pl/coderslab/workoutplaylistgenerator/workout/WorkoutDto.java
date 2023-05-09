package pl.coderslab.workoutplaylistgenerator.workout;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkoutDto {

    @Schema(description = "Workout id", example = "3")
    private Long id;

    @Schema(description = "Type of workout", example = "Zumba", required = true)
    @NotBlank
    private String type;

    @Schema(description = "Intensity of workout", example = "3", required = true)
    private int intensity;

    @Schema(description = "User ID", example = "73658", required = true)
    private Long userId;

    public void setUser_id(Long userId) {
        this.userId = userId;
    }
}
