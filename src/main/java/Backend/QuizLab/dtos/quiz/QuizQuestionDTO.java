package Backend.QuizLab.dtos.quiz;

import Backend.QuizLab.models.commun.QuestionType;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
public class QuizQuestionDTO {
    private Long id;
    private String statement;
    private String additionalInfo;
    private QuestionType questionType;
    private Boolean isRequired;
    private List<QuizOptionDTO> options = new ArrayList<>();
    private Integer correctAttempts = 0;
    private Integer wrongAttempts = 0;
    private Double basePoints = 1D;
}
