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
public class SurveyAnswerRecordDTO {
    private UUID id;
    private UUID questionId;
    private List<UUID> selectedOptions = new ArrayList<>();
    private String textResponse;
}
