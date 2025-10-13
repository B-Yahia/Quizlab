package Backend.QuizLab.models.survey;

import Backend.QuizLab.models.commun.BaseQuestion;
import Backend.QuizLab.models.commun.QuestionType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "survey_questions")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
public class SurveyQuestion extends BaseQuestion {
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SurveyOption> options = new ArrayList<>();
}
