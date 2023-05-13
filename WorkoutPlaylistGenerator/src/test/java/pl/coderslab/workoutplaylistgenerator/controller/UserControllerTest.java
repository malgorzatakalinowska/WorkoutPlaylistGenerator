package pl.coderslab.workoutplaylistgenerator.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.coderslab.workoutplaylistgenerator.user.UserController;
import pl.coderslab.workoutplaylistgenerator.user.UserDto;
import pl.coderslab.workoutplaylistgenerator.user.UserRepository;
import pl.coderslab.workoutplaylistgenerator.user.UserService;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;
    @MockBean
    private UserRepository userRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testGetAllUsers() throws Exception {
        List<UserDto> userList = List.of(
                new UserDto(1L, "Nick", "password", "nick@test.pl"),
                new UserDto(2L, "Steve", "password", "steve@test.pl")
        );
        when(userService.getAllUsers()).thenReturn(userList);

        mockMvc.perform(get("/users/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].displayName", is("Nick")))
                .andExpect(jsonPath("$[0].password", is("password")))
                .andExpect(jsonPath("$[0].email", is("nick@test.pl")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].displayName", is("Steve")))
                .andExpect(jsonPath("$[1].password", is("password")))
                .andExpect(jsonPath("$[1].email", is("steve@test.pl")));
    }

    @Test
    public void testGetUserById() throws Exception {
        Long id = 1L;
        UserDto nick = new UserDto(id, "Nick", "password", "nick@test.pl");

        when(userService.getUser(id)).thenReturn(nick);

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.displayName", is("Nick")))
                .andExpect(jsonPath("$.password", is("password")))
                .andExpect(jsonPath("$.email", is("nick@test.pl")));
    }

    @Test
    public void testCreateUser() throws Exception {
        UserDto steve = new UserDto(1L, "Steve", "password", "steve@test.pl");

        when(userService.createUser(steve))
                .thenReturn(new UserDto(1L, "Steve", "password", "steve@test.pl"));

        mockMvc.perform(post("/users")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(steve)))
                .andExpect(status().isOk());
//                .andExpect(jsonPath("$.id", is(1)))
//                .andExpect(jsonPath("$.displayName", is("Nick")))
//                .andExpect(jsonPath("$.password", is("password")))
//                .andExpect(jsonPath("$.email", is("nick@test.pl")));
    }

    @Test
    public void testDeleteUserById() throws Exception {
        mockMvc.perform(delete("/users/1"))
                .andExpect(status().isNoContent());

        verify(userService, times(1)).deleteUser(1L);
    }
}