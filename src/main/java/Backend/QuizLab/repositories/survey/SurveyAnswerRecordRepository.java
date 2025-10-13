package Backend.QuizLab.repositories.survey;

import Backend.QuizLab.models.survey.SurveyAnswerRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SurveyAnswerRecordRepository extends JpaRepository<SurveyAnswerRecord, UUID> {
}
