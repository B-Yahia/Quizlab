package Backend.QuizLab.models.user;

import Backend.QuizLab.models.quiz.Quiz;
import Backend.QuizLab.models.commun.Review;
import Backend.QuizLab.models.survey.Survey;
import Backend.QuizLab.models.commun.BaseModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
public class User extends BaseModel {
    @NotBlank
    @Size(max = 50)
    private String firstName;

    @NotBlank @Size(max = 50)
    private String lastName;

    @NotBlank @Size(max = 30) @Column(unique = true)
    private String username;

    @NotBlank @Email
    @Column(unique = true)
    private String email;

    @NotBlank @Size(min = 8)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role = Role.REGULAR_USER;

    @OneToMany(mappedBy = "reviewer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "creator")
    private List<Quiz> createdQuizzes = new ArrayList<>();

    @OneToMany(mappedBy = "creator")
    private List<Survey> createdSurveys = new ArrayList<>();

    private boolean isEmailVerified = false;
    private boolean isActive = true;

    public User (String firstName, String lastName, String username, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
