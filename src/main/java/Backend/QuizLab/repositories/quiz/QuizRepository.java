package Backend.QuizLab.repositories.quiz;

import Backend.QuizLab.models.quiz.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface QuizRepository extends JpaRepository<Quiz, UUID> {
    List<Quiz> getAllByCreatorId (UUID creatorId);
    long countByCreatorId (UUID creatorId);
}
