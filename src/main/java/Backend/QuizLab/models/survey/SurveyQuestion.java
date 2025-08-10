package Backend.QuizLab.models.survey;

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
@Table (name = "survey_questions")
@NoArgsConstructor
@Getter
@Setter
public class SurveyQuestion extends BaseQuestion {
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SurveyOption> options = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_id")
    @JsonIgnore
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

    public Survey getSurvey() {
        return survey;
    }
}
