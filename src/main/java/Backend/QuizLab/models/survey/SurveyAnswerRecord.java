package Backend.QuizLab.models.survey;

import Backend.QuizLab.models.commun.BaseModel;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "survey_answer_records")
public class SurveyAnswerRecord extends BaseModel {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private SurveyQuestion question;

    @ManyToMany
    @JoinTable(
            name = "survey_answer_selections",
            joinColumns = @JoinColumn(name = "answer_record_id"),
            inverseJoinColumns = @JoinColumn(name = "option_id")
    )
    private List<SurveyOption> selectedOptions = new ArrayList<>();

    @Column(columnDefinition = "TEXT")
    private String textResponse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attempt_id", nullable = false)
    private SurveyAttempt surveyAttempt;

    public SurveyAnswerRecord ( SurveyQuestion question, List<SurveyOption> selectedOptions , SurveyAttempt surveyAttempt){
        this.question = question;
        this.selectedOptions = selectedOptions;
        this.surveyAttempt = surveyAttempt;
    }
    public SurveyAnswerRecord ( SurveyQuestion question, String textResponse, SurveyAttempt surveyAttempt){
        this.question = question;
        this.textResponse = textResponse;
        this.surveyAttempt = surveyAttempt;
    }
}
