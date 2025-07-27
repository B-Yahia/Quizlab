package Backend.QuizLab.models.survey;

import Backend.QuizLab.models.commun.BaseModel;
import Backend.QuizLab.models.commun.ProgressionStatus;
import Backend.QuizLab.models.user.User;
import jakarta.persistence.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "survey_attempts")
public class SurveyAttempt extends BaseModel {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_id", nullable = false)
    private Survey survey;

    @OneToMany(mappedBy = "surveyAttempt", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SurveyAnswerRecord> answers = new ArrayList<>();

    private Duration duration;

    @Enumerated(EnumType.STRING)
    private ProgressionStatus progressionStatus = ProgressionStatus.IN_PROGRESS;

}
