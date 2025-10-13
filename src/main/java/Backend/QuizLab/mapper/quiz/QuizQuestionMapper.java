package Backend.QuizLab.mapper.quiz;

import Backend.QuizLab.dtos.quiz.QuizQuestionDTO;
import Backend.QuizLab.models.quiz.QuizQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuizQuestionMapper {
    @Autowired
    private QuizOptionMapper optionMapper;

    public QuizQuestion toEntity (QuizQuestionDTO dto){
        var entity = new QuizQuestion();
        entity.setId(dto.getId());
        entity.setOptions(optionMapper.toEntities(dto.getOptions()));
        entity.setQuestionType(dto.getQuestionType());
        entity.setBasePoints(dto.getBasePoints());
        entity.setCorrectAttempts(dto.getCorrectAttempts());
        entity.setWrongAttempts(dto.getWrongAttempts());
        entity.setRequired(dto.getIsRequired());
        entity.setStatement(dto.getStatement());
        entity.setAdditionalInfo(dto.getAdditionalInfo());
        entity.defineQuestionType();
        return entity;
    }

    public QuizQuestion toEntity ( Backend.QuizLab.dtos.llm.quiz.Question aiQuestion){
        var entity = new QuizQuestion();
        entity.setStatement(aiQuestion.statement);
        entity.setAdditionalInfo(aiQuestion.explanation.orElse(""));
        entity.setOptions(optionMapper.toEntities(aiQuestion.correct_options,aiQuestion.other_options));
        entity.defineQuestionType();
        return entity;
    }

    public QuizQuestionDTO toDTO (QuizQuestion entity){
        var dto = new QuizQuestionDTO();
        dto.setId(entity.getId());
        dto.setOptions(optionMapper.toDTOs(entity.getOptions()));
        dto.setQuestionType(entity.getQuestionType());
        dto.setBasePoints(entity.getBasePoints());
        dto.setCorrectAttempts(entity.getCorrectAttempts());
        dto.setWrongAttempts(entity.getWrongAttempts());
        dto.setIsRequired(entity.isRequired());
        dto.setStatement(entity.getStatement());
        dto.setAdditionalInfo(entity.getAdditionalInfo());
        return dto;
    }

    public List<QuizQuestionDTO> toDTOs (List<QuizQuestion> entities){
        List<QuizQuestionDTO> DTOs = new ArrayList<>();
        for (QuizQuestion entity : entities){
            DTOs.add(toDTO(entity));
        }
        return DTOs;
    }

    public List<QuizQuestion> toEntities (List<QuizQuestionDTO> DTOs){
        List<QuizQuestion> entities = new ArrayList<>();
        for (QuizQuestionDTO dto : DTOs){
            entities.add(toEntity(dto));
        }
        return entities;
    }

    public List<QuizQuestion> AIResponseToEntities (List<Backend.QuizLab.dtos.llm.quiz.Question> aiQuestions){
        List<QuizQuestion> entities = new ArrayList<>();
        for (Backend.QuizLab.dtos.llm.quiz.Question aiQuestion : aiQuestions){
            entities.add(toEntity(aiQuestion));
        }
        return entities;
    }
}
