package Backend.QuizLab.services.survey;

import Backend.QuizLab.models.survey.SurveyAnswerRecord;
import Backend.QuizLab.models.survey.SurveyAttempt;
import Backend.QuizLab.repositories.survey.SurveyAttemptRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SurveyAttemptService {

    @Autowired
    private SurveyAttemptRepository attemptRepository;
    @Autowired
    private SurveyAnswerRecordService answerRecordService;

    @Transactional
    public SurveyAttempt create (SurveyAttempt surveyAttempt){
        var attempt = attemptRepository.save(surveyAttempt);
        List<SurveyAnswerRecord> answerRecords = new ArrayList<>();
        for (SurveyAnswerRecord answerRecord : surveyAttempt.getAnswers()){
            answerRecord.setSurveyAttempt(attempt);
            answerRecords.add(answerRecordService.create(answerRecord));
        }
        attempt.setAnswers(answerRecords);
        return attempt;
    }
}
