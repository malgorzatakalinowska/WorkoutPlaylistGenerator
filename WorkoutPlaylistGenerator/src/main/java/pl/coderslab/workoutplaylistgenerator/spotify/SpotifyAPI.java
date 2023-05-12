package pl.coderslab.workoutplaylistgenerator.spotify;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import pl.coderslab.workoutplaylistgenerator.playlist.PlaylistDto;
import pl.coderslab.workoutplaylistgenerator.playlist.PlaylistRepository;
import pl.coderslab.workoutplaylistgenerator.track.Track;
import pl.coderslab.workoutplaylistgenerator.track.TrackDto;
import pl.coderslab.workoutplaylistgenerator.track.TrackMapper;
import pl.coderslab.workoutplaylistgenerator.track.TrackRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class SpotifyAPI {

    private final Track track;
    private final TrackRepository trackRepository;
    private final TrackMapper trackMapper;
    private TrackDto trackDto;
    private final PlaylistDto playlistDto;
    private final PlaylistRepository playlistRepository;

    public SpotifyAPI(Track track, PlaylistDto playlistDto, TrackRepository trackRepository, TrackMapper trackMapper, TrackDto trackDto, PlaylistDto playlistDto1, PlaylistRepository playlistRepository) {
        this.track = track;
        this.trackRepository = trackRepository;
        this.trackMapper = trackMapper;
        this.trackDto = trackDto;
        this.playlistDto = playlistDto1;
        this.playlistRepository = playlistRepository;
    }

    // set access token to connect to api spotify
    String accessToken = "BQAR3X_2_jAadBrvmXuYN7b3MoG_bdLgTFImBiveqFRsRzxbm1XvIuMVnFUomvfub4IkW6N7-W6Kj1FVaooI8CUdzLk0_VYqaxFfPqmgelqQw7w9OcHJKlELKD_9LEPbNl7uCU0tOXvw8lr-_2ZcZlTKJGMxWfZPdaGshCkzzii8m7hiL7UYdyi1i7PGiUBiX60kZxAa-prqtScafkdCHV-2Iw";

    // DOCUMENTATION: https://developer.spotify.com/documentation/web-api/reference/get-recommendations

    public List<Track> getTracksFromSpotify(String url) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Authorization", "Bearer " + accessToken);
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(response.toString());

        List<Track> tracks = new ArrayList<>();
        for (JsonNode trackNode : rootNode.path("tracks")) {
            Track track = new Track();
            track.setSpotifyId(trackNode.path("id").asText());
            track.setName(trackNode.path("name").asText());
            JsonNode artistNode = trackNode.path("artists").get(0);
            track.setArtistName(artistNode.path("name").asText());
            tracks.add(track);
        }
        return tracks;
    }

    public List<Track> getAllTracks() {
        List<Track> tracks = trackRepository.findAll();
        return tracks;
    }

    public Track getTrackById(Long id) {
        Track track = trackRepository.findById(id).orElse(null);
        return track;
    }
}