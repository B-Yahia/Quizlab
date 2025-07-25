package Backend.QuizLab.repositories.quiz;

import Backend.QuizLab.models.quiz.QuizOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizOptionRepository extends JpaRepository<QuizOption,Long> {
}
