package pl.coderslab.workoutplaylistgenerator.workout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final WorkoutMapper workoutMapper;

    public WorkoutService(WorkoutRepository workoutRepository, WorkoutMapper workoutMapper) {
        this.workoutRepository = workoutRepository;
        this.workoutMapper = workoutMapper;
    }

    public WorkoutDto createWorkout(WorkoutDto dto) {
        Workout workout = workoutMapper.mapToEntity(dto);
        Assert.isNull(workout.getId(), "Id has to be null");
        workoutRepository.save(workout);
        return workoutMapper.mapToDto(workout);
    }

    public WorkoutDto getWorkout(Long id) {
        return workoutMapper.mapToDto(workoutRepository.findById(id).orElse(null));
    }

    public List<WorkoutDto> getAllWorkouts() {
        return workoutMapper.mapToDto(workoutRepository.findAll());
    }

    public WorkoutDto updateWorkout(Long id, WorkoutDto dto) {
        Assert.notNull(dto.getId(), "Id cannot be empty");
        if(!dto.getId().equals(id)) {
            throw new IllegalArgumentException("Ids mismatch");
        }
        if(!workoutRepository.existsById(id)) {
            throw new EntityNotFoundException("Workout doesn't exist");
        }
        Workout entity = workoutMapper.mapToEntity(dto);
        workoutRepository.save(entity);
        return workoutMapper.mapToDto(entity);
    }

    public void deleteWorkout(Long id) {
        workoutRepository.deleteById(id);
    }
}