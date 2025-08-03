package Backend.QuizLab.services.quiz;

import Backend.QuizLab.exceptions.ResourceNotFoundException;
import Backend.QuizLab.models.quiz.QuizAnswerRecord;
import Backend.QuizLab.models.quiz.QuizAttempt;
import Backend.QuizLab.repositories.quiz.QuizAttemptRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizAttemptService {

    @Autowired
    private QuizAttemptRepository quizAttemptRepository;
    @Autowired
    private QuizAnswerRecordService answerRecordService;
    @Autowired
    private QuizGradingService gradingService;


    @Transactional
    public QuizAttempt create ( QuizAttempt attempt){
        attempt = gradingService.gradeQuizAttempt(attempt);
        attempt = quizAttemptRepository.save(attempt);
        List<QuizAnswerRecord> records = new ArrayList<>();
        for (QuizAnswerRecord record : attempt.getAnswerRecords()){
            record.setQuizAttempt(attempt);
            answerRecordService.save(record);
            records.add(record);
        }
        attempt.setAnswerRecords(records);
        return attempt;
    }

    public QuizAttempt getById (long id){
        return quizAttemptRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Attempt with the id "+ id +" not found"));
    }

}
