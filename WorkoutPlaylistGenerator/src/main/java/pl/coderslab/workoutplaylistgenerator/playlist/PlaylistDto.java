package pl.coderslab.workoutplaylistgenerator.playlist;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.stereotype.Component;
import pl.coderslab.workoutplaylistgenerator.track.Track;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
public class PlaylistDto {

    @Schema(description = "Playlist id", example = "5")
    private Long id;

    @Schema(description = "Playlist name", example = "Playlist for Zumba", required = true)
    @NotBlank
    private String name;

    @Schema(description = "Playlist description", example = "This is a playlist for a hardcore Zumba class")
    private String description;

    @Schema(description = "User ID", example = "73658", required = true)
    private Long userId;

    @Schema(description = "Workout ID", example = "456", required = true)
    private Long workoutId;

    @Schema(description = "Workout type", required = true)
    private String workoutType;

    @Schema(description = "Track list", required = true)
    private List<Track> tracks = new ArrayList<>();

    public void setUser_id(Long userId) {
        this.userId = userId;
    }

    public void setWorkout_id(Long workoutId) {
        this.workoutId = workoutId;
    }
}