package Backend.QuizLab.dtos.survey;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
public class SurveyAnswerRecordResponse {
    private UUID id;
    private SurveyQuestionDTO question;
    private List<SurveyOptionDTO> selectedOptions = new ArrayList<>();
    private String textResponse;
}
