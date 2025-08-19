package Backend.QuizLab.dtos.quiz;

import Backend.QuizLab.models.user.User;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
public class QuizDTO {
    private Long id;
    private String title;
    private String description;
    private Integer timeLimit;
    private Long creatorId;
    private List<QuizQuestionDTO> questions = new ArrayList<>();
    private Double averageScore;
    private Integer highestScore;
    private Boolean isPublished;
    private Boolean requireAccessCode = false;
    private Boolean allowAnonymous = false;
    private String accessCode;
}
