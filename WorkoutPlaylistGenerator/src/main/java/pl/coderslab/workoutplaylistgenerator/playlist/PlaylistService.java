package pl.coderslab.workoutplaylistgenerator.playlist;

import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import pl.coderslab.workoutplaylistgenerator.exception.ResourceNotFoundException;
import pl.coderslab.workoutplaylistgenerator.track.Track;
import pl.coderslab.workoutplaylistgenerator.track.TrackRepository;
import pl.coderslab.workoutplaylistgenerator.user.User;
import pl.coderslab.workoutplaylistgenerator.user.UserRepository;
import pl.coderslab.workoutplaylistgenerator.workout.Workout;
import pl.coderslab.workoutplaylistgenerator.workout.WorkoutRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlaylistService {

    private final PlaylistRepository playlistRepository;
    private final PlaylistMapper playlistMapper;
    private final UserRepository userRepository;
    private final WorkoutRepository workoutRepository;
    private final TrackRepository trackRepository;

    public PlaylistService(PlaylistRepository playlistRepository, PlaylistMapper playlistMapper, UserRepository userRepository, WorkoutRepository workoutRepository, TrackRepository trackRepository) {
        this.playlistRepository = playlistRepository;
        this.playlistMapper = playlistMapper;
        this.userRepository = userRepository;
        this.workoutRepository = workoutRepository;
        this.trackRepository = trackRepository;
    }

    public PlaylistDto createPlaylist(PlaylistDto playlistDto) {

        if (playlistDto.getUserId() == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        } else {
            playlistDto.setUser_id(playlistDto.getUserId());
        }

        if (playlistDto.getWorkoutId() == null) {
            throw new IllegalArgumentException("Workout ID cannot be null");
        } else {
            playlistDto.setWorkout_id(playlistDto.getWorkoutId());
        }

        User user = userRepository.findById(playlistDto.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found"));
        Workout workout = workoutRepository.findById(playlistDto.getWorkoutId())
                .orElseThrow(() -> new NotFoundException("Workout not found"));

        Playlist playlist = playlistMapper.mapToEntity(playlistDto);
        playlist.setName(workout.getType() + " PLAYLIST");
        playlist.setDescription("Playlist based on my workout preferences");
        playlist.setUser(user);
        playlist.setWorkout(workout);
        playlist = playlistRepository.save(playlist);
        workout = workoutRepository.save(workout);
        playlistDto = playlistMapper.mapToDto(playlist);
        playlistDto.setUserId(user.getId());
        playlistDto.setWorkout_id(workout.getId());
        return playlistDto;
    }

    public PlaylistDto getPlaylist(Long id) {
        Playlist playlist = playlistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Playlist not found"));
        PlaylistDto playlistDto = playlistMapper.mapToDto(playlist);
        playlistDto.setUserId(playlist.getUser().getId());
        playlistDto.setWorkout_id(playlist.getWorkout().getId());
        List<Track> tracks = trackRepository.findAllByPlaylist_Id(id);
        playlistDto.setTracks(tracks);
        return playlistDto;
    }

    public List<PlaylistDto> getAllPlaylists() {
        List<Playlist> playlists = playlistRepository.findAll();
        return playlists.stream()
                .map(playlist -> {
                    PlaylistDto playlistDto = playlistMapper.mapToDto(playlist);
                    playlistDto.setUserId(playlist.getUser().getId());
                    playlistDto.setWorkout_id(playlist.getWorkout().getId());
                    playlistDto.setTracks(trackRepository.findAll());
                    return playlistDto;
                })
                .collect(Collectors.toList());
    }

    public void deletePlaylist(Long id) {
        playlistRepository.deleteById(id);
    }
}