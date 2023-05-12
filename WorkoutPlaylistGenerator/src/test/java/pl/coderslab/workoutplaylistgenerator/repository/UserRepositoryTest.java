package pl.coderslab.workoutplaylistgenerator.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import pl.coderslab.workoutplaylistgenerator.user.User;
import pl.coderslab.workoutplaylistgenerator.user.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DataJpaTest
public class UserRepositoryTest {

    private TestEntityManager testEntityManager;
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
    }

    @Test
    public void find_by_display_name_then_return_user() {
        //given
        final Long id = 10L;
        User user = new User();
        user.setId(id);
        user.setDisplayName("Nick");
        user.setEmail("user@test.pl");
        user.setPassword("coderslab");
        // when
        when(userRepository.findByDisplayName("Nick")).thenReturn(user);
        // then
        User res = userRepository.findByDisplayName("Nick");
    }

    @Test
    public void given_Steve_then_find_Nick_should_be_null() {
        //given
        final Long id = 10L;
        User user = new User();
        user.setId(id);
        user.setDisplayName("Steve");
        user.setEmail("user@test.pl");
        user.setPassword("coderslab");
        // when
        User res = userRepository.findByDisplayName("Nick");
        // then
        assertNull(res);
    }
}