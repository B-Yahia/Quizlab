package Backend.QuizLab.dtos.quiz;

import Backend.QuizLab.models.user.User;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
public class QuizDTO {
    private UUID id;
    private String title;
    private String description;
    private Integer timeLimit;
    private UUID creatorId;
    private List<QuizQuestionDTO> questions = new ArrayList<>();
    private Double averageScore;
    private Integer highestScore;
    private Boolean isPublished;
    private Boolean requireAccessCode = false;
    private Boolean allowAnonymous = false;
    private String accessCode;
    private Long attemptsCount;
}
