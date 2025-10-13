package Backend.QuizLab.dtos.quiz;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
public class QuizOptionDTO {
    private UUID id;
    private String statement;
    private Boolean isCorrect;
}
