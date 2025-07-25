package Backend.QuizLab.repositories.survey;

import Backend.QuizLab.models.survey.SurveyAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyAttemptRepository extends JpaRepository<SurveyAttempt,Long> {
}
