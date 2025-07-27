package Backend.QuizLab.models.quiz;

import Backend.QuizLab.models.commun.BaseQuestion;
import Backend.QuizLab.models.commun.QuestionType;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "quiz_questions")
public class QuizQuestion extends BaseQuestion {
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuizOption> options = new ArrayList<>();

    private int correctAttempts = 0;
    private int wrongAttempts = 0;

    @Column(nullable = false)
    private double basePoints = 1.0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

    public QuizQuestion ( String statement, String additionalInfo, boolean isRequired, double basePoints, Quiz quiz ){
        super(statement,additionalInfo,isRequired);
        this.basePoints = basePoints;
        this.quiz = quiz;
        this.setQuestionType();
    }
    public QuizQuestion ( String statement, boolean isRequired, Quiz quiz ){
        super(statement,isRequired);
        this.quiz = quiz;
        this.setQuestionType();
    }

    private int countCorrectOprions (){
        int numberCorrectAnswers = 0;
        for (QuizOption option : this.options) {
            if (option.isCorrect()) {
                numberCorrectAnswers++;
            }
        }
        return numberCorrectAnswers;
    }

    public void setQuestionType (){
        if (this.countCorrectOprions()==0){
            this.questionType = QuestionType.TEXT;
        }
        if (this.countCorrectOprions()==1){
            this.questionType = QuestionType.CHECKBOX;
        }
        if (this.countCorrectOprions()>1){
            this.questionType = QuestionType.MULTIPLE_CHOICE;
        }
    }

    public void incrementCorrectAttempts(){
        this.correctAttempts++;
    }

    public void incrementWrongAttempts(){
        this.wrongAttempts++;
    }

    public void setBasePoints(double basePoints) {
        this.basePoints = basePoints;
    }

    public void addQuestionOptions(List<QuizOption> options){
        this.options=options;
    }
}
