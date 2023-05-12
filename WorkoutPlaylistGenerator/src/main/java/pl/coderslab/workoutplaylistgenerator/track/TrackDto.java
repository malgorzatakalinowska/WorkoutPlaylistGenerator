package pl.coderslab.workoutplaylistgenerator.track;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
public class TrackDto {

    @Schema(description = "Track id", example = "3")
    private Long id;

    @Schema(description = "Spotify track id", example = "0qt5f5EL92o8Snzopsv0en")
    private String spotifyId;

    @Schema(description = "Artist name", example = "Michael Jackson")
    private String artistName;

    @Schema(description = "Title", example = "Billie Jean")
    private String name;

    @Schema(description = "Playlist ID", example = "22")
    private Long playlistId;
}
