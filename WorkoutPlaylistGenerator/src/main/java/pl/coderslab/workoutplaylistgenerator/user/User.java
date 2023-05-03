package pl.coderslab.workoutplaylistgenerator.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@AllArgsConstructor
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String display_name;

    @NotBlank
    private String password;

    @Email
    @Column(unique = true)
    private String email;
}
