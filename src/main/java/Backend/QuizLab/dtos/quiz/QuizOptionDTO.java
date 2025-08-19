package Backend.QuizLab.dtos.quiz;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
public class QuizOptionDTO {
    private Long id;
    private String statement;
    private Boolean isCorrect;
}
