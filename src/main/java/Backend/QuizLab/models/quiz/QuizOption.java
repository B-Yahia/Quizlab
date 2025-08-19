package Backend.QuizLab.models.quiz;

import Backend.QuizLab.models.commun.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "quiz_options")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class QuizOption extends BaseModel {
    @Column(nullable = false)
    private String statement;

    @Column(nullable = false)
    private boolean isCorrect = false;

}
