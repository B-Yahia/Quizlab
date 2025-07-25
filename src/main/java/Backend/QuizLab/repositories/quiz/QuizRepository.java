package Backend.QuizLab.repositories.quiz;

import Backend.QuizLab.models.quiz.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz,Long> {
}
