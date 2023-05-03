package pl.coderslab.workoutplaylistgenerator.playlist;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.mapping.ToOne;
import pl.coderslab.workoutplaylistgenerator.track.Track;
import pl.coderslab.workoutplaylistgenerator.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@Table(name="playlists")
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @NotBlank
    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(name = "playlist_tracks",
        joinColumns = @JoinColumn(name = "playlist_id"),
        inverseJoinColumns = @JoinColumn(name = "track_id"))
    private List<Track> tracks = new ArrayList<>();
}
