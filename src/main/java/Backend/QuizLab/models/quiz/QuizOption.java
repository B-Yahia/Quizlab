package Backend.QuizLab.models.quiz;

import Backend.QuizLab.models.commun.BaseModel;
import jakarta.persistence.*;

@Entity
@Table(name = "quiz_options")
public class QuizOption extends BaseModel {
    @Column(nullable = false)
    private String statement;

    @Column(nullable = false)
    private boolean isCorrect;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_question_id", nullable = false)
    private QuizQuestion question;

    public QuizOption (String statement, boolean isCorrect){
        this.statement = statement;
        this.isCorrect = isCorrect;
    }

}
