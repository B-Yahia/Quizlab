package Backend.QuizLab.models.quiz;
import Backend.QuizLab.models.commun.BaseModel;
import Backend.QuizLab.models.commun.ProgressionStatus;
import Backend.QuizLab.models.user.User;
import jakarta.persistence.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "quiz_attempts")
public class QuizAttempt extends BaseModel {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

    @OneToMany(mappedBy = "quizAttempt", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuizAnswerRecord> answerRecords = new ArrayList<>();

    private int totalScore;
    private double percentageScore;
    private Duration duration;

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

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public double getPercentageScore() {
        return percentageScore;
    }

    public void setPercentageScore(double percentageScore) {
        this.percentageScore = percentageScore;
    }

    public ProgressionStatus getProgressionStatus() {
        return progressionStatus;
    }

    public void setProgressionStatus(ProgressionStatus progressionStatus) {
        this.progressionStatus = progressionStatus;
    }

    public void addAnswerRecords (List<QuizAnswerRecord> answerRecords){
        this.answerRecords = answerRecords;
    }
}
