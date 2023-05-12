package pl.coderslab.workoutplaylistgenerator.user;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDto createUser(UserDto dto) {
        User user = userMapper.mapToEntity(dto);
        Assert.isNull(user.getId(), "Id has to be null");
        userRepository.save(user);
        return userMapper.mapToDto(user);
    }

    public List<UserDto> getAllUsers() {
        return userMapper.mapToDto(userRepository.findAll());
    }

    public UserDto getUser(Long id) {
        return userMapper.mapToDto(userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found")));
    }

    public Long countAllUsers() {
        return userRepository.count();
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}