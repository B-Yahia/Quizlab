package Backend.QuizLab.dtos.user;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank String identifier, // username or email
        @NotBlank String password
) {
}
