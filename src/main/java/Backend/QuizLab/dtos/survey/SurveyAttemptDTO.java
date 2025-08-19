package Backend.QuizLab.dtos.survey;

import Backend.QuizLab.models.commun.ProgressionStatus;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
public class SurveyAttemptDTO {
    private Long id;
    private Long SurveyId;
    private List<SurveyAnswerRecordDTO> answers;
    private ProgressionStatus progressionStatus;
}
