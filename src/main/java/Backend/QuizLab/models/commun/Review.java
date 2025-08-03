package Backend.QuizLab.models.commun;

import Backend.QuizLab.models.quiz.Quiz;
import Backend.QuizLab.models.survey.Survey;
import Backend.QuizLab.models.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.NonNull;

@Entity
@Table(name = "reviews")
public class Review extends BaseModel {
    @ManyToOne
    private User reviewer;

    @Min(1) @Max(5)
    private int rating;

    @Size(max = 1000)
    private String comment;

    @ManyToOne
    private Quiz quiz;

    @ManyToOne
    private Survey survey;

    public  Review ( User reviewer, int rating ,String comment ,Survey survey){
        this.reviewer = reviewer;
        this.rating = rating;
        this.comment = comment;
        this.quiz = null;
        this.survey = survey;
    }

    public  Review ( User reviewer, int rating , String comment, Quiz quiz){
        this.reviewer = reviewer;
        this.rating = rating;
        this.comment = comment;
        this.quiz = quiz;
        this.survey = null;
    }

}
