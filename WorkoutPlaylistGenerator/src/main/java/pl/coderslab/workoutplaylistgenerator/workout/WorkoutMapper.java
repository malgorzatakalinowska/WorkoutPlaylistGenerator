package pl.coderslab.workoutplaylistgenerator.workout;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WorkoutMapper {

    WorkoutDto mapToDto(Workout workout);

    Workout mapToEntity(WorkoutDto dto);

    List<WorkoutDto> mapToDto(List<Workout> workouts);
}
