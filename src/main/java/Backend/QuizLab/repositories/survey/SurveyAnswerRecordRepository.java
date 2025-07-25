package Backend.QuizLab.repositories.survey;

import Backend.QuizLab.models.survey.SurveyAnswerRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyAnswerRecordRepository extends JpaRepository<SurveyAnswerRecord,Long> {
}
