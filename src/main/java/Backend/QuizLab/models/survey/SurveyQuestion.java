package Backend.QuizLab.models.survey;

import Backend.QuizLab.models.commun.BaseQuestion;
import Backend.QuizLab.models.commun.QuestionType;
import Backend.QuizLab.models.commun.Review;
import Backend.QuizLab.models.quiz.Quiz;
import Backend.QuizLab.models.quiz.QuizOption;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "survey_questions")
public class SurveyQuestion extends BaseQuestion {
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("displayOrder ASC")
    private List<SurveyOption> options = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_id", nullable = false)
    private Survey survey;

    public SurveyQuestion (String statement, String additionalInfo, boolean isRequired, Survey survey, QuestionType questionType){
        super(statement,additionalInfo,isRequired);
        this.survey = survey;
        this.questionType=questionType;
    }

    public SurveyQuestion (String statement, boolean isRequired, Survey survey, QuestionType questionType ){
        super(statement,isRequired);
        this.survey = survey;
        this.questionType=questionType;
    }

    public void addQuestionOptions(List<SurveyOption> options){
        this.options=options;
    }
}
