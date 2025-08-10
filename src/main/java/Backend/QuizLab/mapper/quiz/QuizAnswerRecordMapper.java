package Backend.QuizLab.mapper.quiz;

import Backend.QuizLab.dtos.quiz.QuizAnswerRecordDTO;
import Backend.QuizLab.models.quiz.QuizAnswerRecord;
import Backend.QuizLab.services.quiz.QuizQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuizAnswerRecordMapper {

    @Autowired
    private QuizQuestionService questionService;
    @Autowired
    private QuizOptionMapper optionMapper;

    public QuizAnswerRecord toEntity (QuizAnswerRecordDTO dto){
        var entity = new QuizAnswerRecord();
        entity.setId(dto.getId());
        entity.setQuestion(questionService.getById(dto.getQuestionId()));
        entity.setSelectedOptions(optionMapper.getEntities(dto.getSelectedOptions()));
        entity.setCorrect(dto.isCorrect());
        entity.setTextResponse(dto.getTextResponse());
        entity.setPointsEarned(dto.getPointsEarned());
        entity.setGraded(dto.isGraded());
        return entity;
    }

    public QuizAnswerRecordDTO toDTO (QuizAnswerRecord entity){
        var dto = new QuizAnswerRecordDTO();
        dto.setId(entity.getId());
        dto.setQuestionId(entity.getQuestion().getId());
        dto.setSelectedOptions(optionMapper.getIds(entity.getSelectedOptions()));
        dto.setCorrect(entity.isCorrect());
        dto.setGraded(entity.isGraded());
        dto.setPointsEarned(entity.getPointsEarned());
        dto.setTextResponse(entity.getTextResponse());
        return dto;
    }

    public List<QuizAnswerRecord> toEntities (List<QuizAnswerRecordDTO> DTOs){
        List<QuizAnswerRecord> entities = new ArrayList<>();
        for (QuizAnswerRecordDTO dto :DTOs){
            entities.add(toEntity(dto));
        }
        return entities;
    }

    public List<QuizAnswerRecordDTO> toDTOs(List<QuizAnswerRecord> entities) {
        List<QuizAnswerRecordDTO> DTOs = new ArrayList<>();
        for (QuizAnswerRecord entity : entities){
            DTOs.add(toDTO(entity));
        }
        return DTOs;
    }
}
