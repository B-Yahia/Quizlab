package Backend.QuizLab.dtos.survey;

import Backend.QuizLab.models.commun.QuestionType;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
public class SurveyQuestionDTO {
    private Long id;
    private String statement;
    private String additionalInfo;
    private QuestionType questionType;
    private Boolean isRequired;
    private List<SurveyOptionDTO> options = new ArrayList<>();
}
