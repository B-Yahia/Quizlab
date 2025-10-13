package Backend.QuizLab.services.survey;

import Backend.QuizLab.exceptions.ResourceNotFoundException;
import Backend.QuizLab.models.survey.SurveyAnswerRecord;
import Backend.QuizLab.models.survey.SurveyAttempt;
import Backend.QuizLab.repositories.survey.SurveyAttemptRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class SurveyAttemptService {

    @Autowired
    private SurveyAttemptRepository attemptRepository;
    @Autowired
    private SurveyAnswerRecordService answerRecordService;

    @Transactional
    public SurveyAttempt create (SurveyAttempt surveyAttempt){
        return  attemptRepository.save(surveyAttempt);
    }

    public SurveyAttempt getById (UUID id){
        return attemptRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Survey Attempt with the id  not found"));
    }

    public List<SurveyAttempt> getAll() {
        return attemptRepository.findAll();
    }

    public List<SurveyAttempt> getAllBySurveyId(UUID id) {
        return attemptRepository.getAllBySurveyId(id);
    }

    public Long getCountOfAttemptOnSurvey(UUID id){
        return  attemptRepository.countBySurveyId(id);
    }
}
