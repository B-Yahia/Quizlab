package Backend.QuizLab.services.quiz;

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
            options.add(quizOptionService.create(option));
        }
        question.setOptions(options);
        return question;
    }
}
