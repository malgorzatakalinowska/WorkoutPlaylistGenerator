package pl.coderslab.workoutplaylistgenerator.track;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import pl.coderslab.workoutplaylistgenerator.playlist.Playlist;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tracks")
@Builder
@Component
public class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, name = "track_id")
    private Long id;

    @NotBlank
    @Column(unique = true, name = "spotify_id")
    private String spotifyId;

    @NotBlank
    @Column(name = "artist")
    private String artistName;

    @NotBlank
    @Column(name = "title")
    private String name;

    @ManyToOne
    @JoinColumn(name = "playlist_id")
    private Playlist playlist;
}