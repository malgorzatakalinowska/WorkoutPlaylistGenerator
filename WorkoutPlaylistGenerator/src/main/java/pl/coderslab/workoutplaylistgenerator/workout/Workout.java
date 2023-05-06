package pl.coderslab.workoutplaylistgenerator.workout;

import lombok.*;
import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.Range;
import pl.coderslab.workoutplaylistgenerator.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="workouts")
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, name = "workout_id")
    private Long id;

    @NotBlank
    @Size(min=3, max=15)
    @Column(name = "type")
    private String type;

    @Range(min=1, max=10)
    @Column(name = "intensity")
    private int intensity;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
