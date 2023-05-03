package pl.coderslab.workoutplaylistgenerator.workout;

import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepository;

    public WorkoutService(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    public Workout create(Workout workout) {
        workoutRepository.save(workout);
        return workout;
    }

    public Workout read(Long id) {
        return workoutRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Workout not found"));
    }

    public Workout update(Long id, Workout workout) {
        Workout dbWorkout = read(id);

        if (!dbWorkout.getId().equals(workout.getId())) {
            throw new IllegalArgumentException("Ids mismatch");
        }
        workoutRepository.save(workout);
        return workout;
    }

    public void delete(Long id) {
        workoutRepository.deleteById(id);
    }
}
