package Backend.QuizLab.repositories.quiz;

import Backend.QuizLab.models.quiz.QuizQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface QuizQuestionRepository extends JpaRepository<QuizQuestion, UUID> {
}
