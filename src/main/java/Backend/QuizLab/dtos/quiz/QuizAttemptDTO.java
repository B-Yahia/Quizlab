package Backend.QuizLab.dtos.quiz;

import Backend.QuizLab.models.commun.ProgressionStatus;
import lombok.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
public class QuizAttemptDTO {
    private UUID id;
    private UUID userId;
    private UUID quizId;
    private List<QuizAnswerRecordDTO> answerRecords = new ArrayList<>();
    private Duration duration;
    private ProgressionStatus progressionStatus = ProgressionStatus.IN_PROGRESS;
    private double totalScore;
    private double percentageScore;
    private boolean graded;

}
