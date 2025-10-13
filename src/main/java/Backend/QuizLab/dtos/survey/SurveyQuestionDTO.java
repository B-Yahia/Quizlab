package Backend.QuizLab.dtos.survey;

import Backend.QuizLab.models.commun.QuestionType;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
public class SurveyQuestionDTO {
    private UUID id;
    private String statement;
    private String additionalInfo;
    private QuestionType questionType;
    private Boolean isRequired;
    private List<SurveyOptionDTO> options = new ArrayList<>();
}
