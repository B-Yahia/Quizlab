package Backend.QuizLab.mapper.survey;

import Backend.QuizLab.dtos.survey.SurveyOptionDTO;
import Backend.QuizLab.models.survey.SurveyOption;
import Backend.QuizLab.services.survey.SurveyOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SurveyOptionMapper {
    @Autowired
    private SurveyOptionService optionService;

    public SurveyOption toEntity(SurveyOptionDTO dto) {
        var entity = new SurveyOption();
        entity.setId(dto.getId());
        entity.setStatement(dto.getStatement());
        return entity;
    }

    public SurveyOptionDTO toDTO(SurveyOption entity) {
        var dto = new SurveyOptionDTO();
        dto.setId(entity.getId());
        dto.setStatement(entity.getStatement());
        return dto;
    }

    public List<SurveyOption> getEntities(List<Long> ids) {
        List<SurveyOption> options = new ArrayList<>();
        for (Long id : ids) {
            options.add(optionService.getById(id));
        }
        return options;
    }

    public List<SurveyOption> toEntities(List<SurveyOptionDTO> DTOs) {
        List<SurveyOption> options = new ArrayList<>();
        for (SurveyOptionDTO dto : DTOs) {
            options.add(toEntity(dto));
        }
        return options;
    }

    public List<SurveyOptionDTO> toDTOs(List<SurveyOption> entities) {
        List<SurveyOptionDTO> DTOs = new ArrayList<>();
        for (SurveyOption entity : entities) {
            DTOs.add(toDTO(entity));
        }
        return DTOs;
    }

    public List<Long> getIds(List<SurveyOption> options) {
        List<Long> ids = new ArrayList<>();
        for (SurveyOption option : options) {
            ids.add(option.getId());
        }
        return ids;
    }
}
