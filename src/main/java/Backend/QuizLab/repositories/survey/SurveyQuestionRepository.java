package Backend.QuizLab.repositories.survey;

import Backend.QuizLab.models.survey.SurveyQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyQuestionRepository extends JpaRepository<SurveyQuestion,Long> {
}
