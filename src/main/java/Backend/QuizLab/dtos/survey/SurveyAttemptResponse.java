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
public class SurveyAttemptResponse {
    private UUID id;
    private SurveyDTO survey;
    private List<SurveyAnswerRecordResponse> answers;
    private ProgressionStatus progressionStatus;
}
