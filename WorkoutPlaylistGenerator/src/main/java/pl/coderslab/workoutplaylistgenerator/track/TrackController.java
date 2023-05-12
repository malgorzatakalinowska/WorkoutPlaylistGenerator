package pl.coderslab.workoutplaylistgenerator.track;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.workoutplaylistgenerator.exception.ResourceNotFoundException;
import pl.coderslab.workoutplaylistgenerator.playlist.Playlist;
import pl.coderslab.workoutplaylistgenerator.playlist.PlaylistRepository;
import pl.coderslab.workoutplaylistgenerator.spotify.SpotifyAPI;
import pl.coderslab.workoutplaylistgenerator.workout.WorkoutType;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/tracks")
public class TrackController {

    private final TrackRepository trackRepository;
    private final SpotifyAPI spotifyAPI;
    private final PlaylistRepository playlistRepository;
    private final TrackMapper trackMapper;
    private final Track track;

    public TrackController(TrackRepository trackRepository, SpotifyAPI spotifyAPI, PlaylistRepository playlistRepository, TrackMapper trackMapper, Track track) {
        this.trackRepository = trackRepository;
        this.spotifyAPI = spotifyAPI;
        this.playlistRepository = playlistRepository;
        this.trackMapper = trackMapper;
        this.track = track;
    }
    private static final Logger LOGGER = LoggerFactory.getLogger(SpotifyAPI.class);

    // get tracks for zumba
    String urlZumba = "https://api.spotify.com/v1/recommendations?" +
            "limit=10" +
            "&market=PL" +
            "&seed_artists=329e4yvIujISKGKz1BZZbO" +
            "&seed_genres=reggaeton,latino,dance" +
            "&seed_tracks=5fwSHlTEWpluwOM0Sxnh5k" +
            "&min_danceability=0.7" +
            "&min_energy=0.7" +
            "&min_tempo=130" +
            "&min_valence=0.4";

    // get tracks for biking
    String urlBiking = "https://api.spotify.com/v1/recommendations?" +
            "limit=10" +
            "&market=PL" +
            "&seed_artists=3WOOglGBDGvr6c2WBeMAWn" +
            "&seed_genres=pop,indie-pop,happy" +
            "&seed_tracks=1qrHkGZCxZqFqsHvq7AwqU" +
            "&min_danceability=0.7" +
            "&min_energy=0.7" +
            "&min_tempo=120" +
            "&min_valence=0.85";

    // get tracks for running
    String urlRunning = "https://api.spotify.com/v1/recommendations?" +
            "limit=10" +
            "&market=PL" +
            "&seed_artists=2o5jDhtHVPhrJdv3cEQ99Z" +
            "&seed_genres=power-pop,summer" +
            "&seed_tracks=1rqqCSm0Qe4I9rUvWncaom" +
            "&min_danceability=0.5" +
            "&min_energy=0.9" +
            "&min_tempo=150" +
            "&min_valence=0.6";

    // get tracks for joga
    String urlJoga = "https://api.spotify.com/v1/recommendations?" +
            "limit=10" +
            "&market=PL" +
            "&seed_artists=2o5jDhtHVPhrJdv3cEQ99Z" +
            "&seed_genres=acoustic,sad,sleep" +
            "&seed_tracks=64MCqZFW4qNMyULeHkr5Fs" +
            "&max_danceability=0.2" +
            "&max_energy=0.1" +
            "&max_tempo=80" +
            "&max_valence=0.2";

    @PostMapping("/save")
    public ResponseEntity<?> saveTracks(@RequestParam Long playlistId) {
        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new ResourceNotFoundException("Playlist not found"));

        WorkoutType workoutType = playlist.getWorkout().getType(); // retrieve workout type from the playlist

        String url;
        switch (workoutType) {
            case ZUMBA:
                url = urlZumba;
                break;
            case BIKING:
                url = urlBiking;
                break;
            case RUNNING:
                url = urlRunning;
                break;
            case JOGA:
                url = urlJoga;
                break;
            default:
                throw new IllegalArgumentException("Invalid workout type: " + workoutType);
        }
        try {
            List<Track> tracks = spotifyAPI.getTracksFromSpotify(url);
            tracks.forEach(track -> track.setPlaylist(playlist)); // set the playlist for each track

            trackRepository.saveAll(tracks);

            return ResponseEntity.ok().build();
        } catch (IOException e) {
            LOGGER.error("An error occurred while getting tracks from Spotify API: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while getting tracks from Spotify API");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Track>> getAllTracks() {
        List<Track> tracks = spotifyAPI.getAllTracks();
        return ResponseEntity.ok(tracks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Track> getTrackById(@PathVariable Long id) {
        Track track = spotifyAPI.getTrackById(id);
        return ResponseEntity.ok(track);
    }
}