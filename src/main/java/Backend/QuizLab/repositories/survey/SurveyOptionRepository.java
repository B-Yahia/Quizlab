package Backend.QuizLab.repositories.survey;

import Backend.QuizLab.models.survey.SurveyOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SurveyOptionRepository extends JpaRepository<SurveyOption, UUID> {
}
