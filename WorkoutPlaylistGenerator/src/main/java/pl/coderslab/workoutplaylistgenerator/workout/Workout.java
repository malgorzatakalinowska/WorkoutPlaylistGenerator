package pl.coderslab.workoutplaylistgenerator.workout;

import lombok.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.coderslab.workoutplaylistgenerator.user.User;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "workouts")
@Builder
@Component
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, name = "workout_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private WorkoutType type;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}