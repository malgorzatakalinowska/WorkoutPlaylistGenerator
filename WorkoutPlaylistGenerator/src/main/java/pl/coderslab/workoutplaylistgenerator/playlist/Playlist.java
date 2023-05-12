package pl.coderslab.workoutplaylistgenerator.playlist;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.coderslab.workoutplaylistgenerator.user.User;
import pl.coderslab.workoutplaylistgenerator.workout.Workout;
import pl.coderslab.workoutplaylistgenerator.workout.WorkoutType;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="playlists")
@Builder
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, name = "playlist_id")
    private Long id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "workout_id")
    private Workout workout;

    @Enumerated(EnumType.STRING)
    @Column(name = "workout_type")
    private WorkoutType workoutType;
}