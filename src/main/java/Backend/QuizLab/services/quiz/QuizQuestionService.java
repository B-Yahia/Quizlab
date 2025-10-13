package Backend.QuizLab.services.quiz;

import Backend.QuizLab.exceptions.ResourceNotFoundException;
import Backend.QuizLab.models.quiz.QuizOption;
import Backend.QuizLab.models.quiz.QuizQuestion;
import Backend.QuizLab.repositories.quiz.QuizQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class QuizQuestionService {

    @Autowired
    private QuizQuestionRepository quizQuestionRepository;
    @Autowired
    private QuizOptionService quizOptionService;

    public QuizQuestion create (QuizQuestion quizQuestion){
        return  quizQuestionRepository.save(quizQuestion);
    }

    public QuizQuestion getById (UUID id){
        return quizQuestionRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Quiz Question with the id "+ id +" not found"));
    }
}
