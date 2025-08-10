package Backend.QuizLab.models.survey;

import Backend.QuizLab.models.commun.BaseModel;
import Backend.QuizLab.models.commun.ProgressionStatus;
import Backend.QuizLab.models.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "survey_attempts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SurveyAttempt extends BaseModel {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_id")
    private Survey survey;

    @OneToMany(mappedBy = "surveyAttempt", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SurveyAnswerRecord> answers = new ArrayList<>();

    private Duration duration;

    @Enumerated(EnumType.STRING)
    private ProgressionStatus progressionStatus = ProgressionStatus.IN_PROGRESS;

    public SurveyAttempt ( User user, Survey survey, ProgressionStatus progressionStatus ){
        this.user = user;
        this.survey = survey;
        this.progressionStatus = progressionStatus;
    }

    public SurveyAttempt ( User user, Survey survey, Duration duration, ProgressionStatus progressionStatus ){
        this.user = user;
        this.survey = survey;
        this.duration = duration;
        this.progressionStatus = progressionStatus;
    }

    public List<SurveyAnswerRecord> getAnswers() {
        return answers;
    }

    public void setAnswers(List<SurveyAnswerRecord> answers) {
        this.answers = answers;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public ProgressionStatus getProgressionStatus() {
        return progressionStatus;
    }

    public void setProgressionStatus(ProgressionStatus progressionStatus) {
        this.progressionStatus = progressionStatus;
    }
}
