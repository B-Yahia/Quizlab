package Backend.QuizLab.dtos.survey;

import Backend.QuizLab.models.commun.ProgressionStatus;
import lombok.*;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
public class SurveyAttemptDTO {
    private UUID id;
    private UUID SurveyId;
    private List<SurveyAnswerRecordDTO> answers;
    private ProgressionStatus progressionStatus;
}
