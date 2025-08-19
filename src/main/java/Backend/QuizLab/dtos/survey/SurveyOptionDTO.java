package Backend.QuizLab.dtos.survey;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
public class SurveyOptionDTO {
    private Long id;
    private String statement;
}
