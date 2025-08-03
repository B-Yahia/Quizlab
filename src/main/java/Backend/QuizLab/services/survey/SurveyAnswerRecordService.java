package Backend.QuizLab.services.survey;

import Backend.QuizLab.exceptions.ResourceNotFoundException;
import Backend.QuizLab.models.survey.SurveyAnswerRecord;
import Backend.QuizLab.repositories.survey.SurveyAnswerRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SurveyAnswerRecordService {

    @Autowired
    private SurveyAnswerRecordRepository recordRepository;

    public SurveyAnswerRecord create ( SurveyAnswerRecord surveyAnswerRecord ){
        return recordRepository.save(surveyAnswerRecord);
    }

    public SurveyAnswerRecord getById (long id){
        return recordRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Answer Record with id "+ id +" not found"));
    }
}
