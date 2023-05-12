package pl.coderslab.workoutplaylistgenerator.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.coderslab.workoutplaylistgenerator.user.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @InjectMocks
    private UserService service;
    @Mock
    private UserRepository repository;
    @Mock
    private UserMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void return_user_by_id() {
        // given
        final Long id = 10L;
        User user = new User();
        user.setId(id);
        user.setDisplayName("User");
        user.setEmail("user@test.pl");
        user.setPassword("coderslab");
        UserDto userDto = new UserDto();
        userDto.setId(id);
        userDto.setDisplayName("User");
        userDto.setEmail("user@test.pl");
        userDto.setPassword("coderslab");
        // when
        when(repository.findById(id)).thenReturn(Optional.of(user));
        when(mapper.mapToDto(user)).thenReturn(userDto);
        UserDto res = service.getUser(id);
        // then
        assertNotNull(res);
        assertEquals(userDto, res);
    }
}