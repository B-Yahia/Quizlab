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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id")
    @JsonIgnore
    private User creator;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true)
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

    @OneToMany(mappedBy = "quiz")
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    private List<QuizAttempt> quizAttempts = new ArrayList<>();

    // Statistics
    private Double averageScore = 0.0;
    private Integer highestScore = 0;

    // Settings
    private boolean isPublished = false;
    private boolean requireAccessCode = false;
    private boolean allowAnonymous = false;

    @Column(length = 50)
    private String accessCode;

    public Quiz (String title, String description, Integer timeLimit, User creator, List<Category> categories, List<Tag> tags, boolean isPublished) {
        this.title = title;
        this.description = description;
        this.timeLimit = timeLimit;
        this.creator = creator;
        this.categories = categories;
        this.tags = tags;
        this.isPublished = isPublished;
    }

    public Quiz (String title, String description, Integer timeLimit, User creator, List<Category> categories, List<Tag> tags, boolean isPublished, String accessCode) {
        this.title = title;
        this.description = description;
        this.timeLimit = timeLimit;
        this.creator = creator;
        this.categories = categories;
        this.tags = tags;
        this.isPublished = isPublished;
        this.requireAccessCode = true;
        this.accessCode = accessCode;
    }
}
