package Backend.QuizLab.models.quiz;

import Backend.QuizLab.models.commun.BaseModel;
import Backend.QuizLab.models.commun.QuestionType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "quiz_answer_records")
@NoArgsConstructor
@Getter
@Setter
public class QuizAnswerRecord extends BaseModel {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private QuizQuestion question;

    @ManyToMany
    @JoinTable(
            name = "quiz_answer_selections",
            joinColumns = @JoinColumn(name = "answer_record_id"),
            inverseJoinColumns = @JoinColumn(name = "option_id")
    )
    private List<QuizOption> selectedOptions = new ArrayList<>();

    @Column(columnDefinition = "TEXT")
    private String textResponse;

    private boolean isCorrect;
    private double pointsEarned;
    private boolean graded = false;

}
