package Backend.QuizLab.models.commun;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
public abstract class BaseQuestion extends BaseModel {
    @Column(nullable = false, length = 1000)
    private String statement;

    @Column(length = 500)
    private String additionalInfo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    protected QuestionType questionType;

    @Column(nullable = false)
    private boolean isRequired = true;

    public BaseQuestion ( String statement, String additionalInfo, boolean isRequired){
        this.statement = statement;
        this.additionalInfo = additionalInfo;
        this.isRequired = isRequired;
    }

    public BaseQuestion ( String statement, boolean isRequired){
        this.statement = statement;
        this.isRequired = isRequired;
    }
}
