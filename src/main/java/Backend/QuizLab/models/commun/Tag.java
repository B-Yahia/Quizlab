package Backend.QuizLab.models.commun;

import Backend.QuizLab.models.quiz.Quiz;
import Backend.QuizLab.models.survey.Survey;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tags")
@Getter
@Setter

public class Tag extends BaseModel {
    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "tags")
    private List<Quiz> quizzes = new ArrayList<>();

    @ManyToMany(mappedBy = "tags")
    private List<Survey> surveys = new ArrayList<>();

    public Tag (String name){
        this.name = name;
    }
}
