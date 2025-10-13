package Backend.QuizLab.services.quiz;

import Backend.QuizLab.exceptions.ResourceNotFoundException;
import Backend.QuizLab.models.quiz.QuizAnswerRecord;
import Backend.QuizLab.repositories.quiz.QuizAnswerRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class QuizAnswerRecordService {

    @Autowired
    private QuizAnswerRecordRepository quizAnswerRecordRepository;

    public QuizAnswerRecord save (QuizAnswerRecord record){
        return  quizAnswerRecordRepository.save(record);
    }

    public QuizAnswerRecord getById (UUID id ){
        return quizAnswerRecordRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Answer Record with id "+ id +" not found"));
    }


}
