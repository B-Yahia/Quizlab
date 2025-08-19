package Backend.QuizLab.services.survey;

import Backend.QuizLab.exceptions.ResourceNotFoundException;
import Backend.QuizLab.models.survey.Survey;
import Backend.QuizLab.repositories.survey.SurveyRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurveyService {

    @Autowired
    private SurveyRepository surveyRepository;
    @Autowired
    private SurveyQuestionService surveyQuestionService;

    @Transactional
    public Survey create (Survey survey){
        return surveyRepository.save(survey);
    }

    public Survey getById (long id){
        return surveyRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Survey with the id "+ id +" not found"));
    }

    public List<Survey> getAll (){
        return surveyRepository.findAll();
    }
}
