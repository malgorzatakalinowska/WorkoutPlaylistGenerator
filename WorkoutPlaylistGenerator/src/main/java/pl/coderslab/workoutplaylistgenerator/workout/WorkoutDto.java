package pl.coderslab.workoutplaylistgenerator.workout;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.workoutplaylistgenerator.user.User;

@Getter
@Setter
public class WorkoutDto {
    private Long id;
    private String type;
    private int intensity;
    private User user;
}
