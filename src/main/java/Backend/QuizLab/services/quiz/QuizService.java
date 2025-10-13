package Backend.QuizLab.services.quiz;

import Backend.QuizLab.exceptions.ResourceNotFoundException;
import Backend.QuizLab.models.quiz.Quiz;
import Backend.QuizLab.repositories.quiz.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private QuizQuestionService questionService;

    public Quiz create( Quiz quiz){
        return quizRepository.save(quiz);
    }

    public Quiz getById(UUID id){
        return quizRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Quiz with the id "+ id +" not found"));
    }

    public List<Quiz> getAll (){
        return quizRepository.findAll();
    }

    public List<Quiz> getAllByCreatorId (UUID id){
        return quizRepository.getAllByCreatorId(id);
    }

    public long getCountOfUserQuizzes (UUID id){
        return quizRepository.countByCreatorId(id);
    }
}
