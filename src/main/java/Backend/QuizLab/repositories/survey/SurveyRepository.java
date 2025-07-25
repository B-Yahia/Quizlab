package Backend.QuizLab.repositories.survey;

import Backend.QuizLab.models.survey.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyRepository extends JpaRepository<Survey,Long> {
}
