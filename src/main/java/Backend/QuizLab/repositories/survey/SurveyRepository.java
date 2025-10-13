package Backend.QuizLab.repositories.survey;

import Backend.QuizLab.models.survey.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SurveyRepository extends JpaRepository<Survey, UUID> {
    List<Survey> getAllByCreatorId (UUID creatorId);
    long countByCreatorId (UUID creatorId);
}
