package Backend.QuizLab.models.quiz;

import Backend.QuizLab.models.commun.BaseQuestion;
import Backend.QuizLab.models.commun.QuestionType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "quiz_questions")
@NoArgsConstructor
@Getter
@Setter
public class QuizQuestion extends BaseQuestion {
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuizOption> options = new ArrayList<>();

    private int correctAttempts = 0;
    private int wrongAttempts = 0;

    @Column(nullable = false)
    private double basePoints = 1.0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id")
    @JsonIgnore
    private Quiz quiz;

    public QuizQuestion ( String statement, String additionalInfo, boolean isRequired, double basePoints, Quiz quiz , List<QuizOption> options){
        super(statement,additionalInfo,isRequired);
        this.basePoints = basePoints;
        this.quiz = quiz;
        this.options = options;
        this.setQuestionType();
    }
    public QuizQuestion ( String statement, boolean isRequired, Quiz quiz, List<QuizOption> options){
        super(statement,isRequired);
        this.quiz = quiz;
        this.options = options;
        this.setQuestionType();
    }
    public QuizQuestion ( String statement, boolean isRequired, Quiz quiz){
        super(statement,isRequired);
        this.quiz = quiz;
        this.setQuestionType();
    }

    private long countCorrectOptions() {
        return options.stream()
                .filter(QuizOption::isCorrect)
                .count();
    }

    public void setQuestionType(){
        int optionCount = options == null ? 0 : options.size();
        long correctCount = countCorrectOptions();

        if (optionCount >= 2 && correctCount == 1) {
            this.questionType = QuestionType.CHECKBOX;
        } else if (optionCount >= 2 && correctCount >= 2) {
            this.questionType = QuestionType.MULTIPLE_CHOICE;
        } else {
            this.questionType = QuestionType.TEXT;
        }
    }

    public void incrementCorrectAttempts(){
        this.correctAttempts++;
    }

    public void incrementWrongAttempts(){
        this.wrongAttempts++;
    }

    public void addQuestionOptions(List<QuizOption> options){
        this.options=options;
    }

    public void setOptions(List<QuizOption> options) {
        this.options = options;
        this.setQuestionType();
    }
}
