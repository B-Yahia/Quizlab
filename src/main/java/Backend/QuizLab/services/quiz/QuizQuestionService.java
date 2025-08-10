package Backend.QuizLab.services.quiz;

import Backend.QuizLab.exceptions.ResourceNotFoundException;
import Backend.QuizLab.models.quiz.QuizOption;
import Backend.QuizLab.models.quiz.QuizQuestion;
import Backend.QuizLab.repositories.quiz.QuizQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizQuestionService {

    @Autowired
    private QuizQuestionRepository quizQuestionRepository;
    @Autowired
    private QuizOptionService quizOptionService;

    public QuizQuestion create (QuizQuestion quizQuestion){
        var question = quizQuestionRepository.save(quizQuestion);
        List<QuizOption> options = new ArrayList<>();
        for (QuizOption option : quizQuestion.getOptions()){
            option.setQuestion(question);
            var savedOption = quizOptionService.create(option);
            options.add(savedOption);
        }
        question.setOptions(options);
        return question;
    }

    public QuizQuestion getById (long id){
        return quizQuestionRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Quiz Question with the id "+ id +" not found"));
    }
}
