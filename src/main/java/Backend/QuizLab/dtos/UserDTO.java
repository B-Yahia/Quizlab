package Backend.QuizLab.dtos;

import Backend.QuizLab.models.user.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
public class UserDTO {
    private Long id;
    private String firstName;
    @NotBlank @Size(max = 50)
    private String lastName;
    @NotBlank @Size(max = 30)
    private String username;
    @NotBlank @Email
    private String email;
    @NotBlank @Size(min = 8)
    private String password;
    private Role role = Role.REGULAR_USER;
    private boolean isEmailVerified = false;
    private boolean isActive = true;
}
