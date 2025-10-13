package Backend.QuizLab.services.survey;

import Backend.QuizLab.exceptions.ResourceNotFoundException;
import Backend.QuizLab.models.survey.Survey;
import Backend.QuizLab.repositories.survey.SurveyRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

    public Survey getById (UUID id){
        return surveyRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Survey with the id "+ id +" not found"));
    }

    public List<Survey> getAll (){
        return surveyRepository.findAll();
    }

    public List<Survey> getAllByCreatorId ( UUID id){
        return surveyRepository.getAllByCreatorId(id);
    }

    public long getCountsOfUserSurveys (UUID id){
        return surveyRepository.countByCreatorId(id);
    }
}
