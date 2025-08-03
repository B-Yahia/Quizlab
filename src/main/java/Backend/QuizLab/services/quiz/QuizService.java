package Backend.QuizLab.services.quiz;

import Backend.QuizLab.models.quiz.Quiz;
import Backend.QuizLab.models.quiz.QuizQuestion;
import Backend.QuizLab.repositories.quiz.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private QuizQuestionService questionService;

    public Quiz create( Quiz quiz){
        var newQuiz = quizRepository.save(quiz);
        List<QuizQuestion> questions = new ArrayList<>();
        for(QuizQuestion question : quiz.getQuestions()){
            question.setQuiz(newQuiz);
            questions.add(questionService.create(question));
        }
        newQuiz.setQuestions(questions);
        return newQuiz;
    }
}
