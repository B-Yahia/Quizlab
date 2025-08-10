package Backend.QuizLab.models.survey;

import Backend.QuizLab.models.commun.BaseModel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "survey_options")
@Builder
@Getter
@Setter
public class SurveyOption extends BaseModel {
    @Column(nullable = false)
    private String statement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_question_id")
    @JsonIgnore
    private SurveyQuestion question;

    public SurveyOption ( String statement, SurveyQuestion question ){
        this.statement = statement;
        this.question = question;
    }
    public SurveyOption ( String statement ){
        this.statement = statement;
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
