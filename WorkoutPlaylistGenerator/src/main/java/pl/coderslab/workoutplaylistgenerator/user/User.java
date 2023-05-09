package pl.coderslab.workoutplaylistgenerator.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, name = "user_id")
    private Long id;

    @Column(name = "display_name")
    private String displayName;

    @NotBlank
    @Column(name = "password")
    private String password;

    @Email
    @Column(unique = true, name = "email")
    private String email;

    public User(String displayName, String password, String email) {
        this.displayName = displayName;
        this.password = password;
        this.email = email;
    }
}