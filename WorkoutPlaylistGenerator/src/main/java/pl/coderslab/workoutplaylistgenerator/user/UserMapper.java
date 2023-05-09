package pl.coderslab.workoutplaylistgenerator.user;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto mapToDto(User user);

    User mapToEntity(UserDto dto);

    List<UserDto> mapToDto(List<User> users);
}