package Backend.QuizLab.repositories.quiz;

import Backend.QuizLab.models.quiz.QuizOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface QuizOptionRepository extends JpaRepository<QuizOption, UUID> {
}
