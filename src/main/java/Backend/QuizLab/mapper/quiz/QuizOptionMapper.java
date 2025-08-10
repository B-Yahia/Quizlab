package Backend.QuizLab.mapper.quiz;

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

    public List<QuizOption> getEntities (List<Long> ids){
        List<QuizOption> options = new ArrayList<>();
        for (Long id :ids){
            options.add(optionService.getById(id));
        }
        return options;
    }

    public List<Long> getIds (List<QuizOption> options){
        List<Long> ids = new ArrayList<>();
        for (QuizOption option : options){
            ids.add(option.getId());
        }
        return ids;
    }
}
