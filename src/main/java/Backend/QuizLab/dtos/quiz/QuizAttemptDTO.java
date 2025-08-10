package Backend.QuizLab.dtos.quiz;

import Backend.QuizLab.models.commun.ProgressionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class QuizAttemptDTO {
    private Long id;
    private Long userId;
    private Long quizId;
    private List<QuizAnswerRecordDTO> answerRecords = new ArrayList<>();
    private Duration duration;
    private ProgressionStatus progressionStatus = ProgressionStatus.IN_PROGRESS;
    private double totalScore;
    private double percentageScore;
    private boolean graded;

    @Override
    public String toString() {
        return "QuizAttemptDTO{" +
                "id=" + id +
                ", userId=" + userId +
                ", quizId=" + quizId +
                ", answerRecords=" + answerRecords +
                ", duration=" + duration +
                ", progressionStatus=" + progressionStatus +
                ", totalScore=" + totalScore +
                ", percentageScore=" + percentageScore +
                ", graded=" + graded +
                '}';
    }
}
