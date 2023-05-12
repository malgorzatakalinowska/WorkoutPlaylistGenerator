package pl.coderslab.workoutplaylistgenerator.track;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TrackMapper {

    TrackDto mapToDto(Track track);

    Track mapToEntity(TrackDto dto);

    List<TrackDto> mapToDto(List<Track> tracks);
    List<Track> mapToEntity(List<TrackDto> tracks);

}