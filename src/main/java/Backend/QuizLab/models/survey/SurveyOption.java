package Backend.QuizLab.models.survey;

import Backend.QuizLab.models.commun.BaseModel;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "survey_options")
@Builder
public class SurveyOption extends BaseModel {
    @Column(nullable = false)
    private String statement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_question_id")
    private SurveyQuestion question;

    public SurveyOption ( String statement, SurveyQuestion question ){
        this.statement = statement;
        this.question = question;
    }

    public SurveyOption(){}

    public void setQuestion(SurveyQuestion question) {
        this.question = question;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public SurveyQuestion getQuestion() {
        return question;
    }
}
