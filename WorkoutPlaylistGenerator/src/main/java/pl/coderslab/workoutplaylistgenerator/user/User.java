package pl.coderslab.workoutplaylistgenerator.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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
}
