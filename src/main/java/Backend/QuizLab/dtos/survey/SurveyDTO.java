package Backend.QuizLab.dtos.survey;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
public class SurveyDTO {
    private UUID id;
    private String title;
    private String description;
    private UUID creatorId;
    private List<SurveyQuestionDTO> questions = new ArrayList<>();
    private Boolean isPublished;
    private Boolean requireAccessCode = false;
    private Boolean allowAnonymous = false;
    private String accessCode;
    private Long attemptsCount;
}
