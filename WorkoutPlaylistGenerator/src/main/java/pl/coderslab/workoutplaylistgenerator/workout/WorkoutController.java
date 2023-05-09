package pl.coderslab.workoutplaylistgenerator.workout;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/workouts")
public class WorkoutController {

    private final WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WorkoutDto createWorkout(@RequestBody WorkoutDto workout) {
        return workoutService.createWorkout(workout);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkoutDto> getWorkout(@PathVariable Long id) {
        WorkoutDto dto = workoutService.getWorkout(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<WorkoutDto>> getAllWorkouts() {
        List<WorkoutDto> workouts = workoutService.getAllWorkouts();
        if (workouts.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(workouts);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkoutDto> updateWorkout(@PathVariable Long id, @RequestBody @Valid WorkoutDto workout) {
        WorkoutDto dto = workoutService.updateWorkout(id, workout);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteWorkout(@PathVariable Long id) {
        workoutService.deleteWorkout(id);
        return ResponseEntity.noContent().build();
    }
}
