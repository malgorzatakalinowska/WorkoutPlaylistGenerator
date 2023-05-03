package pl.coderslab.workoutplaylistgenerator.workout;

public interface WorkoutMapper {

    WorkoutDto mapWorkoutToDto(Workout workout);

    Workout mapDtoToUser(WorkoutDto workoutDto);
}
