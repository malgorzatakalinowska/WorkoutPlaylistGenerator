package pl.coderslab.workoutplaylistgenerator.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI springOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Workout Playlist Generator Application API")
                        .description("Sample application with Spring Boot and OpenAPI")
                        .version("v1.1.1"));
    }
}
