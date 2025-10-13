package Backend.QuizLab.mapper.quiz;

import Backend.QuizLab.dtos.quiz.QuizOptionDTO;
import Backend.QuizLab.models.quiz.QuizOption;
import Backend.QuizLab.services.quiz.QuizOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    public List<QuizOption> getEntities (List<UUID> ids){
        List<QuizOption> options = new ArrayList<>();
        for (UUID id :ids){
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

    public List<QuizOption> toEntities (List<String> correctOptions, List<String> wrongOptions){
        List<QuizOption> options = new ArrayList<>();
        for (String op :correctOptions){
            var correctOption = new QuizOption();
            correctOption.setStatement(op);
            correctOption.setCorrect(true);
            options.add(correctOption);
        }
        for (String op: wrongOptions){
            var wrongOption = new QuizOption();
            wrongOption.setStatement(op);
            wrongOption.setCorrect(false);
            options.add(wrongOption);
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

    public List<UUID> getIds (List<QuizOption> options){
        List<UUID> ids = new ArrayList<>();
        for (QuizOption option : options){
            ids.add(option.getId());
        }
        return ids;
    }
}
