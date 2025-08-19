package Backend.QuizLab.models.quiz;

import Backend.QuizLab.models.commun.BaseModel;
import Backend.QuizLab.models.commun.Category;
import Backend.QuizLab.models.commun.Review;
import Backend.QuizLab.models.commun.Tag;
import Backend.QuizLab.models.survey.SurveyQuestion;
import Backend.QuizLab.models.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "quizzes")
@NoArgsConstructor
@Getter
@Setter
public class Quiz extends BaseModel {
    @Column(nullable = false)
    private String title;

    @Column(length = 2000)
    private String description;

    private Integer timeLimit;

    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuizQuestion> questions = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "quiz_categories",
            joinColumns = @JoinColumn(name = "quiz_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "quiz_tags",
            joinColumns = @JoinColumn(name = "quiz_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags = new ArrayList<>();

    @OneToMany (cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "creator_id", nullable = false)
    private User creator;

    // Statistics
    private Double averageScore = 0.0;
    private Integer highestScore = 0;
    // Settings
    private Boolean isPublished = false;
    private Boolean requireAccessCode = false;
    private Boolean allowAnonymous = false;

    @Column(length = 50)
    private String accessCode;

}
