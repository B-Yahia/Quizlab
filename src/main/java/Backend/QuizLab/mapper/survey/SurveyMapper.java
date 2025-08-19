package Backend.QuizLab.mapper.survey;

import Backend.QuizLab.dtos.survey.SurveyDTO;
import Backend.QuizLab.models.survey.Survey;
import Backend.QuizLab.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SurveyMapper {

    @Autowired
    private SurveyQuestionMapper questionMapper;

    @Autowired
    private UserService userService;

    public Survey toEntity(SurveyDTO dto) {
        var entity = new Survey();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setCreator(userService.getUserById(dto.getCreatorId()));
        entity.setQuestions(questionMapper.toEntities(dto.getQuestions()));
        entity.setPublished(dto.getIsPublished());
        entity.setRequireAccessCode(dto.getRequireAccessCode());
        entity.setAllowAnonymous(dto.getAllowAnonymous());
        entity.setAccessCode(dto.getAccessCode());
        return entity;
    }

    public SurveyDTO toDTO(Survey entity) {
        var dto = new SurveyDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setCreatorId(entity.getCreator().getId());
        dto.setQuestions(questionMapper.toDTOs(entity.getQuestions()));
        dto.setIsPublished(entity.isPublished());
        dto.setRequireAccessCode(entity.isRequireAccessCode());
        dto.setAllowAnonymous(entity.isAllowAnonymous());
        dto.setAccessCode(entity.getAccessCode());
        return dto;
    }

    public List<SurveyDTO> toDTOs(List<Survey> entities) {
        List<SurveyDTO> DTOs = new ArrayList<>();
        for (Survey entity : entities) {
            DTOs.add(toDTO(entity));
        }
        return DTOs;
    }

    public List<Survey> toEntities(List<SurveyDTO> DTOs) {
        List<Survey> entities = new ArrayList<>();
        for (SurveyDTO dto : DTOs) {
            entities.add(toEntity(dto));
        }
        return entities;
    }
}
