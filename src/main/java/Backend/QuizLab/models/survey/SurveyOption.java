package Backend.QuizLab.models.survey;

import Backend.QuizLab.models.commun.BaseModel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "survey_options")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SurveyOption extends BaseModel {
    @Column(nullable = false)
    private String statement;
}
