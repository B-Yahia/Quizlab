package Backend.QuizLab.models.quiz;

import Backend.QuizLab.models.commun.BaseModel;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "quiz_options")
@NoArgsConstructor
public class QuizOption extends BaseModel {
    @Column(nullable = false)
    private String statement;

    @Column(nullable = false)
    private boolean isCorrect;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_question_id")
    private QuizQuestion question;

    public QuizOption ( String statement, boolean isCorrect, QuizQuestion question ) {
        this.statement = statement;
        this.isCorrect = isCorrect;
        this.question = question;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setQuestion( QuizQuestion question ) {
        this.question = question;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }
}
