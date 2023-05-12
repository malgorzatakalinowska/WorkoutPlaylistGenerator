package pl.coderslab.workoutplaylistgenerator.playlist;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {

    private final PlaylistService playlistService;
    private final PlaylistMapper playlistMapper;

    public PlaylistController(PlaylistService playlistService, PlaylistMapper playlistMapper) {
        this.playlistService = playlistService;
        this.playlistMapper = playlistMapper;
    }

    @PostMapping
    public ResponseEntity<PlaylistDto> createPlaylist(@RequestBody PlaylistDto playlist) {
        PlaylistDto playlistDto = playlistService.createPlaylist(playlist);
        return ResponseEntity.ok(playlistDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity getPlaylist(@PathVariable Long id) {
        PlaylistDto dto = playlistService.getPlaylist(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<PlaylistDto>> getAllPlaylists() {
        List<PlaylistDto> playlists = playlistService.getAllPlaylists();
        if (playlists.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(playlists);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePlaylist(@PathVariable Long id) {
        playlistService.deletePlaylist(id);
        return ResponseEntity.noContent().build();
    }
}