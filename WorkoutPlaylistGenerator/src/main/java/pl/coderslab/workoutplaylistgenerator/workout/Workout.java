package pl.coderslab.workoutplaylistgenerator.workout;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import pl.coderslab.workoutplaylistgenerator.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Data
@AllArgsConstructor
@Table(name="workouts")
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min=3, max=15)
    private String type;

    @Range(min=1, max=10)
    private int intensity;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
