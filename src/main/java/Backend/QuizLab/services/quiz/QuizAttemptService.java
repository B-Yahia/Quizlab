package Backend.QuizLab.services.quiz;

import Backend.QuizLab.exceptions.ResourceNotFoundException;
import Backend.QuizLab.models.quiz.QuizAttempt;
import Backend.QuizLab.repositories.quiz.QuizAttemptRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class QuizAttemptService {

    @Autowired
    private QuizAttemptRepository quizAttemptRepository;
    @Autowired
    private QuizGradingService gradingService;


    @Transactional
    public QuizAttempt create ( QuizAttempt attempt){
        attempt = gradingService.gradeQuizAttempt(attempt);
        return quizAttemptRepository.save(attempt);
    }

    public QuizAttempt getById (UUID id){
        return quizAttemptRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Quiz attempt with id "+ id +" not found"));
    }

    public List<QuizAttempt> getAll (){
        return quizAttemptRepository.findAll();
    }

    public List<QuizAttempt> getAllByQuizId (UUID id){
        return quizAttemptRepository.getAllByQuizId(id);
    }

    public Long getCountOfAttemptOnQuiz(UUID id){
        return  quizAttemptRepository.countByQuizId(id);
    }

}
