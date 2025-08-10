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
@Table(name = "categories")
@Getter
@Setter
public class Category extends BaseModel {
    @Column(nullable = false, unique = true)
    private String name;

    @Column(length = 1000)
    private String description;

    @ManyToMany(mappedBy = "categories")
    private List<Quiz> quizzes = new ArrayList<>();

    @ManyToMany(mappedBy = "categories")
    private List<Survey> surveys = new ArrayList<>();
}
