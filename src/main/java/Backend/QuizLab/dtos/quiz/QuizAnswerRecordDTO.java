package Backend.QuizLab.dtos.quiz;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class QuizAnswerRecordDTO {
    private Long id;
    private Long questionId;
    private List<Long> selectedOptions = new ArrayList<>();
    private String textResponse;
    private boolean isCorrect;
    private double pointsEarned;
    private boolean graded;

    @Override
    public String toString() {
        return "QuizAnswerRecordDTO{" +
                "id=" + id +
                ", questionId=" + questionId +
                ", selectedOptions=" + selectedOptions +
                ", textResponse='" + textResponse + '\'' +
                ", isCorrect=" + isCorrect +
                ", pointsEarned=" + pointsEarned +
                ", graded=" + graded +
                '}';
    }
}
