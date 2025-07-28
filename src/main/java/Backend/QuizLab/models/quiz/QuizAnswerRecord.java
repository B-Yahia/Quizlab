package Backend.QuizLab.models.quiz;

import Backend.QuizLab.models.commun.BaseModel;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "quiz_answer_records")
public class QuizAnswerRecord extends BaseModel {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private QuizQuestion question;

    @ManyToMany
    @JoinTable(
            name = "quiz_answer_selections",
            joinColumns = @JoinColumn(name = "answer_record_id"),
            inverseJoinColumns = @JoinColumn(name = "option_id")
    )
    private List<QuizOption> selectedOptions = new ArrayList<>();

    @Column(columnDefinition = "TEXT")
    private String textResponse;

    private boolean isCorrect;
    private double pointsEarned;
    private boolean graded = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attempt_id", nullable = false)
    private QuizAttempt quizAttempt;

    public QuizAnswerRecord (QuizQuestion question,  List<QuizOption> selectedOptions, QuizAttempt quizAttempt){
        this.question = question;
        this.selectedOptions = selectedOptions;
        this.quizAttempt = quizAttempt;
    }

    public QuizAnswerRecord (QuizQuestion question,  String textResponse, QuizAttempt quizAttempt){
        this.question = question;
        this.textResponse = textResponse;
        this.quizAttempt = quizAttempt;
    }

    public void setPointsEarned(double pointsEarned) {
        this.pointsEarned = pointsEarned;
    }

    public void setGraded(boolean graded) {
        this.graded = graded;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }
}
