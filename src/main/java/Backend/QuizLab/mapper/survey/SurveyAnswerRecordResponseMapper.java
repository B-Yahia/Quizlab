package Backend.QuizLab.mapper.survey;

import Backend.QuizLab.dtos.survey.SurveyAnswerRecordResponse;
import Backend.QuizLab.models.survey.SurveyAnswerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SurveyAnswerRecordResponseMapper {
    @Autowired
    private SurveyQuestionMapper questionMapper;
    @Autowired
    private SurveyOptionMapper optionMapper;

    public SurveyAnswerRecordResponse toResponse (SurveyAnswerRecord entity){
        var dto = new SurveyAnswerRecordResponse();
        dto.setId(entity.getId());
        dto.setQuestion(questionMapper.toDTO(entity.getQuestion()));
        dto.setTextResponse(entity.getTextResponse());
        dto.setSelectedOptions(optionMapper.toDTOs(entity.getSelectedOptions()));
        return dto;
    }

    public List<SurveyAnswerRecordResponse> toResponses (List<SurveyAnswerRecord> entities){
        List<SurveyAnswerRecordResponse> DTOs = new ArrayList<>();
        for (SurveyAnswerRecord entity : entities){
            DTOs.add(toResponse(entity));
        }
        return DTOs;
    }
}
