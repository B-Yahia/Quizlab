package Backend.QuizLab.repositories.survey;

import Backend.QuizLab.models.survey.SurveyOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyOptionRepository extends JpaRepository<SurveyOption,Long> {
}
