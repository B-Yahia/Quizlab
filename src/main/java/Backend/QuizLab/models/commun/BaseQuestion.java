package Backend.QuizLab.models.commun;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseQuestion extends BaseModel {
    @Column(nullable = false, length = 1000)
    private String statement;

    @Column(length = 500)
    private String additionalInfo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private QuestionType questionType;

    @Column(nullable = false)
    private boolean isRequired = true;
}
