package pl.coderslab.workoutplaylistgenerator.workout;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/workout")
@Slf4j
public class WorkoutController {

    private final WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @PostMapping
    public Workout createWorkout(@RequestBody Workout workout) {
        workoutService.create(workout);
        log.debug("workout {}", workout);
        return workout;
    }

    @GetMapping("/{id}")
    public Workout getWorkout(@PathVariable Long id) {
        return workoutService.read(id);
    }

    @PutMapping("/{id}")
    public Workout updateWorkout(@PathVariable Long id, @RequestBody Workout workout) {
        return workoutService.update(id, workout);
    }

    @DeleteMapping("/{id}")
    public void deleteWorkout(@PathVariable Long id) {
        workoutService.delete(id);
    }
}
