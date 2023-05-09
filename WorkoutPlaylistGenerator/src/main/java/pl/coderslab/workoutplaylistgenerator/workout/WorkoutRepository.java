package pl.coderslab.workoutplaylistgenerator.workout;

import org.hibernate.jdbc.Work;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {

}
