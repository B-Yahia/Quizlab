package Backend.QuizLab.dtos.survey;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
public class SurveyAnswerRecordDTO {
    private Long id;
    private Long questionId;
    private List<Long> selectedOptions = new ArrayList<>();
    private String textResponse;
}
