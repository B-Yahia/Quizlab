package Backend.QuizLab.mapper.quiz;

import Backend.QuizLab.dtos.quiz.QuizOptionDTO;
import Backend.QuizLab.models.quiz.QuizOption;
import Backend.QuizLab.services.quiz.QuizOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuizOptionMapper {

    @Autowired
    private QuizOptionService optionService;

    public QuizOption toEntity ( QuizOptionDTO dto ){
        var entity = new QuizOption();
        entity.setId(dto.getId());
        entity.setStatement(dto.getStatement());
        entity.setCorrect(dto.getIsCorrect());
        return entity;
    }

    public QuizOptionDTO toDTO (QuizOption entity){
        var dto = new QuizOptionDTO();
        dto.setId(entity.getId());
        dto.setStatement(entity.getStatement());
        dto.setIsCorrect(entity.isCorrect());
        return dto;
    }
    public List<QuizOption> getEntities (List<Long> ids){
        List<QuizOption> options = new ArrayList<>();
        for (Long id :ids){
            options.add(optionService.getById(id));
        }
        return options;
    }

    public List<QuizOption> toEntities (List<QuizOptionDTO> DTOs){
        List<QuizOption> options = new ArrayList<>();
        for (QuizOptionDTO dto :DTOs){
            options.add(toEntity(dto));
        }
        return options;
    }

    public List<QuizOptionDTO> toDTOs (List<QuizOption> entities){
        List<QuizOptionDTO> DTOs = new ArrayList<>();
        for (QuizOption entity :entities){
            DTOs.add(toDTO(entity));
        }
        return DTOs;
    }

    public List<Long> getIds (List<QuizOption> options){
        List<Long> ids = new ArrayList<>();
        for (QuizOption option : options){
            ids.add(option.getId());
        }
        return ids;
    }
}
