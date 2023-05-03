package pl.coderslab.workoutplaylistgenerator.track;

import lombok.AllArgsConstructor;
import lombok.Data;
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
@Table(name="tracks")
public class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @NotBlank
    private String spotifyId;

    @Range(min=0, max=1)
    private float danceability; // range 0.0-1.0

    @Range(min=0, max=1)
    private float energy; // range 0.0-1.0

    @Range(min=0, max=1)
    private float valence; // range 0.0-1.0, high valence -> more positive

    @Max(200)
    private float tempo;

    @ManyToMany(mappedBy = "tracks")
    private List<Playlist> playlists = new ArrayList<>();

}
