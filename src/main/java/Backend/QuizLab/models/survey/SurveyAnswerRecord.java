package Backend.QuizLab.models.survey;

import Backend.QuizLab.models.commun.BaseModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "survey_answer_records")
@NoArgsConstructor
@Getter
@Setter
public class SurveyAnswerRecord extends BaseModel {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
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
}
