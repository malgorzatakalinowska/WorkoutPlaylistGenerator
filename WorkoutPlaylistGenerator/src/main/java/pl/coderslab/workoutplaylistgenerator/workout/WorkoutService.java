package pl.coderslab.workoutplaylistgenerator.workout;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.webjars.NotFoundException;
import pl.coderslab.workoutplaylistgenerator.exception.IdMismatchException;
import pl.coderslab.workoutplaylistgenerator.exception.ResourceNotFoundException;
import pl.coderslab.workoutplaylistgenerator.user.User;
import pl.coderslab.workoutplaylistgenerator.user.UserRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final WorkoutMapper workoutMapper;
    private final UserRepository userRepository;

    public WorkoutService(WorkoutRepository workoutRepository, WorkoutMapper workoutMapper, UserRepository userRepository) {
        this.workoutRepository = workoutRepository;
        this.workoutMapper = workoutMapper;
        this.userRepository = userRepository;
    }

    public WorkoutDto createWorkout(WorkoutDto workoutDto) {
        if (workoutDto.getUserId() == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        } else {
            workoutDto.setUserId(workoutDto.getUserId());
        }
        User user = userRepository.findById(workoutDto.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found"));
        Workout workout = workoutMapper.mapToEntity(workoutDto);
        workout.setUser(user);
        workout = workoutRepository.save(workout);
        workoutDto = workoutMapper.mapToDto(workout);
        workoutDto.setUserId(user.getId());
        return workoutDto;
    }

    public WorkoutDto getWorkout(Long id) {
        Workout workout = workoutRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Workout not found"));
        WorkoutDto workoutDto = workoutMapper.mapToDto(workout);
        workoutDto.setUserId(workout.getUser().getId());
        return workoutDto;
    }

    public List<WorkoutDto> getAllWorkouts() {
        List<Workout> workouts = workoutRepository.findAll();
        return workouts.stream()
                .map(workout -> {
                    WorkoutDto workoutDto = workoutMapper.mapToDto(workout);
                    workoutDto.setUserId(workout.getUser().getId());
                    return workoutDto;
                })
                .collect(Collectors.toList());
    }

    public WorkoutDto updateWorkout(Long id, WorkoutDto dto) {
        Assert.notNull(dto.getId(), "Id cannot be empty");
        if (!dto.getId().equals(id)) {
            throw new IdMismatchException("Ids mismatch");
        }
        if (!workoutRepository.existsById(id)) {
            throw new ResourceNotFoundException("Workout doesn't exist");
        }
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found"));
        Workout workout = workoutMapper.mapToEntity(dto);
        workout.setId(id);
        workout.setUser(user);
        workout = workoutRepository.save(workout);
        dto = workoutMapper.mapToDto(workout);
        dto.setId(workout.getId());
        dto.setUserId(user.getId());
        return dto;
    }

    public void deleteWorkout(Long id) {
        workoutRepository.deleteById(id);
    }
}