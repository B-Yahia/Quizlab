package Backend.QuizLab.mapper.survey;

import Backend.QuizLab.dtos.survey.SurveyAttemptResponse;
import Backend.QuizLab.models.survey.SurveyAttempt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SurveyAttemptResponseMapper {
    @Autowired
    private SurveyMapper surveyMapper;
    @Autowired
    private SurveyAnswerRecordResponseMapper surveyAnswerRecordResponseMapper;

    public SurveyAttemptResponse toResponse (SurveyAttempt entity){
        var response = new SurveyAttemptResponse();
        response.setId(entity.getId());
        response.setSurvey(surveyMapper.toDTO(entity.getSurvey()));
        response.setProgressionStatus(entity.getProgressionStatus());
        response.setAnswers(surveyAnswerRecordResponseMapper.toResponses(entity.getAnswers()));
        return response;
    }

    public List<SurveyAttemptResponse> toResponses (List<SurveyAttempt> entities){
        List<SurveyAttemptResponse> responses = new ArrayList<>();
        for (SurveyAttempt entity : entities){
            responses.add(toResponse(entity));
        }
        return responses;
    }

}
