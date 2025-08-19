package Backend.QuizLab.mapper.survey;

import Backend.QuizLab.dtos.survey.SurveyAttemptDTO;
import Backend.QuizLab.models.survey.SurveyAttempt;
import Backend.QuizLab.services.survey.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SurveyAttemptMapper {
    @Autowired
    private SurveyAnswerRecordMapper recordMapper;
    @Autowired
    private SurveyService service;

    public SurveyAttempt toEntity (SurveyAttemptDTO dto ){
        var entity = new SurveyAttempt();
        entity.setId(dto.getId());
        entity.setSurvey(service.getById(dto.getSurveyId()));
        entity.setAnswers(recordMapper.toEntities(dto.getAnswers()));
        entity.setProgressionStatus(dto.getProgressionStatus());
        return entity;
    }

    public SurveyAttemptDTO toDTO (SurveyAttempt entity){
        var dto = new SurveyAttemptDTO();
        dto.setId(entity.getId());
        dto.setSurveyId(entity.getSurvey().getId());
        dto.setProgressionStatus(entity.getProgressionStatus());
        dto.setAnswers(recordMapper.toDTOs(entity.getAnswers()));
        return dto;
    }

}
