package Backend.QuizLab.repositories.quiz;

import Backend.QuizLab.models.quiz.QuizAnswerRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface QuizAnswerRecordRepository extends JpaRepository<QuizAnswerRecord, UUID> {
}
