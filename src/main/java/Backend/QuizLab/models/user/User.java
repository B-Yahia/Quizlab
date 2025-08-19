package Backend.QuizLab.models.user;

import Backend.QuizLab.models.commun.BaseModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class User extends BaseModel {

    @NotBlank
    @Size(max = 50)
    private String firstName;

    @NotBlank @Size(max = 50)
    private String lastName;

    @NotBlank @Size(max = 30) @Column(unique = true)
    private String username;

    @NotBlank @Email
    @Column(unique = true)
    private String email;

    @NotBlank @Size(min = 8)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role = Role.REGULAR_USER;

    private boolean isEmailVerified = false;
    private boolean isActive = true;

}
