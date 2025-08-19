package Backend.QuizLab.models.quiz;

import Backend.QuizLab.models.commun.BaseQuestion;
import Backend.QuizLab.models.commun.QuestionType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "quiz_questions")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class QuizQuestion extends BaseQuestion {
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true ,fetch =  FetchType.EAGER)
    private List<QuizOption> options = new ArrayList<>();

    private Integer correctAttempts = 0;
    private Integer wrongAttempts = 0;

    @Column(nullable = false)
    private Double basePoints = 1.0;

    private long countCorrectOptions() {
        return options.stream()
                .filter(QuizOption::isCorrect)
                .count();
    }

    public void defineQuestionType(){
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

}
