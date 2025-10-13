package Backend.QuizLab.dtos.quiz;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
public class QuizAnswerRecordDTO {
    private UUID id;
    private UUID questionId;
    private List<UUID> selectedOptions = new ArrayList<>();
    private String textResponse;
    private boolean isCorrect;
    private double pointsEarned;
    private boolean graded;
}
