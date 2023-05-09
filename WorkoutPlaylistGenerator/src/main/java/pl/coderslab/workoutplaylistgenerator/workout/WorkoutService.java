package pl.coderslab.workoutplaylistgenerator.workout;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.webjars.NotFoundException;
import pl.coderslab.workoutplaylistgenerator.exception.IdMismatchException;
import pl.coderslab.workoutplaylistgenerator.exception.ResourceNotFoundException;
import pl.coderslab.workoutplaylistgenerator.user.User;
import pl.coderslab.workoutplaylistgenerator.user.UserRepository;

import java.util.List;


@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final WorkoutMapper workoutMapper;
    private final UserRepository userRepository;
    private final User user;

    public WorkoutService(WorkoutRepository workoutRepository, WorkoutMapper workoutMapper, UserRepository userRepository, User user) {
        this.workoutRepository = workoutRepository;
        this.workoutMapper = workoutMapper;
        this.userRepository = userRepository;
        this.user = user;
    }

    public WorkoutDto createWorkout(WorkoutDto workoutDto) {
        if (workoutDto.getUserId() == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        } else {
            workoutDto.setUser_id(workoutDto.getUserId());
        }
        User user = userRepository.findById(workoutDto.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found"));
        Workout workout = workoutMapper.mapToEntity(workoutDto);
        workout.setUser(user);
        workout = workoutRepository.save(workout);
        workoutDto = workoutMapper.mapToDto(workout);
        workoutDto.setUser_id(user.getId());
        return workoutDto;
    }

    public WorkoutDto getWorkout(Long id) {
        return workoutMapper.mapToDto(workoutRepository.findById(id).orElse(null));    }

    public List<WorkoutDto> getAllWorkouts(){
        return workoutMapper.mapToDto(workoutRepository.findAll());
    }

    public WorkoutDto updateWorkout(Long id, WorkoutDto dto) {
        Assert.notNull(dto.getId(), "Id cannot be empty");
        if(!dto.getId().equals(id)) {
            throw new IdMismatchException("Ids mismatch");
        }
        if(!workoutRepository.existsById(id)) {
            throw new ResourceNotFoundException("Workout doesn't exist");
        }
        Workout entity = workoutMapper.mapToEntity(dto);
        workoutRepository.save(entity);
        return workoutMapper.mapToDto(entity);
    }

    public void deleteWorkout(Long id) {
        workoutRepository.deleteById(id);
    }
}