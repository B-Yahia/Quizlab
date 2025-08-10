package Backend.QuizLab.models.quiz;

import Backend.QuizLab.models.commun.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "quiz_options")
@NoArgsConstructor
@Getter
@Setter
public class QuizOption extends BaseModel {
    @Column(nullable = false)
    private String statement;

    @Column(nullable = false)
    private boolean isCorrect;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_question_id")
    @JsonIgnore
    private QuizQuestion question;

    public QuizOption ( String statement, boolean isCorrect, QuizQuestion question ) {
        this.statement = statement;
        this.isCorrect = isCorrect;
        this.question = question;
    }
    public QuizOption ( String statement, boolean isCorrect ) {
        this.statement = statement;
        this.isCorrect = isCorrect;
    }
}
