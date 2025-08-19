package Backend.QuizLab.models.survey;

import Backend.QuizLab.models.commun.BaseModel;
import Backend.QuizLab.models.commun.Category;
import Backend.QuizLab.models.commun.Review;
import Backend.QuizLab.models.commun.Tag;
import Backend.QuizLab.models.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "surveys")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Survey extends BaseModel {
    @Column(nullable = false)
    private String title;

    @Column(length = 2000)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id",nullable = false)
    private User creator;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SurveyQuestion> questions = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "survey_categories",
            joinColumns = @JoinColumn(name = "survey_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "survey_tags",
            joinColumns = @JoinColumn(name = "survey_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    private boolean isPublished = false;
    private boolean allowAnonymous = false;
    private boolean requireAccessCode = false;

    @Column(length = 50)
    private String accessCode;

}
