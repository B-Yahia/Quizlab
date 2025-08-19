package Backend.QuizLab.mapper.survey;

import Backend.QuizLab.dtos.survey.SurveyQuestionDTO;
import Backend.QuizLab.models.survey.SurveyQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SurveyQuestionMapper {
    @Autowired
    private SurveyOptionMapper optionMapper;

    public SurveyQuestion toEntity(SurveyQuestionDTO dto) {
        var entity = new SurveyQuestion();
        entity.setId(dto.getId());
        entity.setOptions(optionMapper.toEntities(dto.getOptions()));
        entity.setQuestionType(dto.getQuestionType());
        entity.setRequired(dto.getIsRequired());
        entity.setStatement(dto.getStatement());
        entity.setAdditionalInfo(dto.getAdditionalInfo());
        return entity;
    }

    public SurveyQuestionDTO toDTO(SurveyQuestion entity) {
        var dto = new SurveyQuestionDTO();
        dto.setId(entity.getId());
        dto.setOptions(optionMapper.toDTOs(entity.getOptions()));
        dto.setQuestionType(entity.getQuestionType());
        dto.setIsRequired(entity.isRequired());
        dto.setStatement(entity.getStatement());
        dto.setAdditionalInfo(entity.getAdditionalInfo());
        return dto;
    }

    public List<SurveyQuestionDTO> toDTOs(List<SurveyQuestion> entities) {
        List<SurveyQuestionDTO> DTOs = new ArrayList<>();
        for (SurveyQuestion entity : entities) {
            DTOs.add(toDTO(entity));
        }
        return DTOs;
    }

    public List<SurveyQuestion> toEntities(List<SurveyQuestionDTO> DTOs) {
        List<SurveyQuestion> entities = new ArrayList<>();
        for (SurveyQuestionDTO dto : DTOs) {
            entities.add(toEntity(dto));
        }
        return entities;
    }
}
