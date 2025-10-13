package Backend.QuizLab.services.quiz;

import Backend.QuizLab.exceptions.ResourceNotFoundException;
import Backend.QuizLab.models.quiz.QuizOption;
import Backend.QuizLab.repositories.quiz.QuizOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class QuizOptionService {

    @Autowired
    private QuizOptionRepository repository;

    public QuizOption create ( QuizOption quizOption) {
        return repository.save(quizOption);
    }

    public QuizOption getById ( UUID id) {
        return repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Option with the id " + id + " not found"));
    }
}
