package Backend.QuizLab.repositories.quiz;

import Backend.QuizLab.models.quiz.QuizAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizAttemptRepository extends JpaRepository<QuizAttempt,Long> {
}
