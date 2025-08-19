package Backend.QuizLab.controllers.auth;

import Backend.QuizLab.models.user.Role;

public record UserResponse(
        Long id,
        String firstName,
        String lastName,
        String username,
        String email,
        Role role,
        boolean emailVerified,
        boolean active
) {
}
