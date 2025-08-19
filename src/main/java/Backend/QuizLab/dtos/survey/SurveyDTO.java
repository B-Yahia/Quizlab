package Backend.QuizLab.dtos.survey;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
public class SurveyDTO {
    private Long id;
    private String title;
    private String description;
    private Long creatorId;
    private List<SurveyQuestionDTO> questions = new ArrayList<>();
    private Boolean isPublished;
    private Boolean requireAccessCode = false;
    private Boolean allowAnonymous = false;
    private String accessCode;
}
