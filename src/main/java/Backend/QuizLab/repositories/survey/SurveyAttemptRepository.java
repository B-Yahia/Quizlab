package Backend.QuizLab.repositories.survey;

import Backend.QuizLab.models.survey.SurveyAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SurveyAttemptRepository extends JpaRepository<SurveyAttempt, UUID> {
    List<SurveyAttempt> getAllBySurveyId (UUID surveyId);
    long countBySurveyId(UUID surveyId);
}
