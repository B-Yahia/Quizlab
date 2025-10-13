package Backend.QuizLab.repositories.quiz;

import Backend.QuizLab.models.quiz.QuizAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface QuizAttemptRepository extends JpaRepository<QuizAttempt, UUID> {
    List<QuizAttempt> getAllByQuizId (UUID quizId);
    long countByQuizId(UUID quizId);
}
