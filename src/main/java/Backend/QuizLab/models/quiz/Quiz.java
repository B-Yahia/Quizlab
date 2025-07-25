package Backend.QuizLab.models.quiz;

import Backend.QuizLab.models.commun.BaseModel;
import Backend.QuizLab.models.commun.Category;
import Backend.QuizLab.models.commun.Review;
import Backend.QuizLab.models.commun.Tag;
import Backend.QuizLab.models.user.User;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "quizzes")
public class Quiz extends BaseModel {
    @Column(nullable = false)
    private String title;

    @Column(length = 2000)
    private String description;

    private Integer timeLimit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id")
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
    private List<QuizAttempt> participations = new ArrayList<>();

    // Statistics
    private Double averageScore = 0.0;
    private Integer highestScore = 0;

    // Settings
    private boolean isPublic = true;
    private boolean isPublished = false;
    private boolean requireAccessCode = false;

    @Column(length = 50)
    private String accessCode;
}
