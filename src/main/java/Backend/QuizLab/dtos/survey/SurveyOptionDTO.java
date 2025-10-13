package Backend.QuizLab.dtos.survey;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
public class SurveyOptionDTO {
    private UUID id;
    private String statement;
}
