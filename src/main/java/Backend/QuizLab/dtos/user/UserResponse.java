package Backend.QuizLab.dtos.user;

import Backend.QuizLab.models.user.Role;

import java.util.UUID;

public record UserResponse(
        UUID id,
        String firstName,
        String lastName,
        String username,
        String email,
        Role role,
        boolean emailVerified,
        boolean active,
        Long numberCreatedQuizzes,
        Long numberOfCreatedSurveys
) {
}
