package pl.coderslab.workoutplaylistgenerator.playlist;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlaylistMapper {

    PlaylistDto mapToDto(Playlist playlist);

    Playlist mapToEntity(PlaylistDto dto);

    List<PlaylistDto> mapToDto(List<Playlist> playlists);
}