package pl.coderslab.workoutplaylistgenerator.track;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import pl.coderslab.workoutplaylistgenerator.playlist.Playlist;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tracks")
public class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, name = "track_id")
    private Long id;

    @NotBlank
    @Column(unique = true, name = "spotify_id")
    private String spotifyId;

    @Range(min=0, max=1)
    @Column(name = "danceability")
    private float danceability; // range 0.0-1.0

    @Range(min=0, max=1)
    @Column(name = "energy")
    private float energy; // range 0.0-1.0

    @Range(min=0, max=1)
    @Column(name = "valence")
    private float valence; // range 0.0-1.0, high valence -> more positive

    @Max(200)
    @Column(name = "tempo")
    private float tempo;

    @ManyToMany(mappedBy = "tracks")
    private List<Playlist> playlists = new ArrayList<>();

}
