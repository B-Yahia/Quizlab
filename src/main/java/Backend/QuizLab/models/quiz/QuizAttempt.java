package Backend.QuizLab.models.quiz;
import Backend.QuizLab.models.commun.BaseModel;
import Backend.QuizLab.models.commun.ProgressionStatus;
import Backend.QuizLab.models.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "quiz_attempts")
@NoArgsConstructor
@Getter
@Setter
public class QuizAttempt extends BaseModel {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id")
    @JsonIgnore
    private Quiz quiz;

    @OneToMany(mappedBy = "quizAttempt", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuizAnswerRecord> answerRecords = new ArrayList<>();

    private double totalScore;
    private double percentageScore;
    private Duration duration;
    private boolean graded = false;

    @Enumerated(EnumType.STRING)
    private ProgressionStatus progressionStatus = ProgressionStatus.IN_PROGRESS;

    public QuizAttempt ( User user, Quiz quiz, Duration duration, ProgressionStatus progressionStatus){
        this.user = user;
        this.quiz = quiz;
        this.duration = duration;
        this.progressionStatus = progressionStatus;
    }

    public QuizAttempt ( User user, Quiz quiz, ProgressionStatus progressionStatus){
        this.user = user;
        this.quiz = quiz;
        this.progressionStatus = progressionStatus;
    }

}
