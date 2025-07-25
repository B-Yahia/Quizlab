package Backend.QuizLab.models.survey;

import Backend.QuizLab.models.commun.BaseModel;
import jakarta.persistence.*;

@Entity
@Table(name = "survey_options")
public class SurveyOption extends BaseModel {
    @Column(nullable = false)
    private String statement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_question_id", nullable = false)
    private SurveyQuestion question;
}
