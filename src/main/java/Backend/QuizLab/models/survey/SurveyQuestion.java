package Backend.QuizLab.models.survey;

import Backend.QuizLab.models.commun.BaseQuestion;
import Backend.QuizLab.models.commun.QuestionType;
import Backend.QuizLab.models.commun.Review;
import Backend.QuizLab.models.quiz.Quiz;
import Backend.QuizLab.models.quiz.QuizOption;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "survey_questions")
@NoArgsConstructor
public class SurveyQuestion extends BaseQuestion {
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SurveyOption> options = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_id")
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

    public void setOptions(List<SurveyOption> options) {
        this.options = options;
    }

    public List<SurveyOption> getOptions() {
        return options;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }
}
