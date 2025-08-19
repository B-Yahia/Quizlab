package Backend.QuizLab.mapper.survey;

import Backend.QuizLab.dtos.survey.SurveyAnswerRecordDTO;
import Backend.QuizLab.models.survey.SurveyAnswerRecord;
import Backend.QuizLab.services.survey.SurveyOptionService;
import Backend.QuizLab.services.survey.SurveyQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SurveyAnswerRecordMapper {
    @Autowired
    private SurveyQuestionService questionService;
    @Autowired
    private SurveyOptionService optionService;
    @Autowired
    private SurveyOptionMapper optionMapper;


    public SurveyAnswerRecordDTO toDTO (SurveyAnswerRecord entity){
        var dto = new SurveyAnswerRecordDTO();
        dto.setId(entity.getId());
        dto.setQuestionId(entity.getQuestion().getId());
        dto.setSelectedOptions(optionMapper.getIds(entity.getSelectedOptions()));
        dto.setTextResponse(entity.getTextResponse());
        return dto;
    }

    public SurveyAnswerRecord toEntity (SurveyAnswerRecordDTO dto){
        var entity = new SurveyAnswerRecord();
        entity.setId(dto.getId());
        entity.setQuestion(questionService.getById(dto.getQuestionId()));
        entity.setSelectedOptions(optionMapper.getEntities(dto.getSelectedOptions()));
        entity.setTextResponse(dto.getTextResponse());
        return entity;
    }

    public List<SurveyAnswerRecord> toEntities (List<SurveyAnswerRecordDTO> DTOs){
        List<SurveyAnswerRecord> entities = new ArrayList<>();
        for (SurveyAnswerRecordDTO dto : DTOs){
            entities.add(toEntity(dto));
        }
        return entities;
    }

    public List<SurveyAnswerRecordDTO> toDTOs (List<SurveyAnswerRecord> entities){
        List<SurveyAnswerRecordDTO> DTOs = new ArrayList<>();
        for (SurveyAnswerRecord entity : entities){
            DTOs.add(toDTO(entity));
        }
        return DTOs;
    }
}
