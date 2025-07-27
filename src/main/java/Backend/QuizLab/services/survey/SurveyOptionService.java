package Backend.QuizLab.services.survey;

import Backend.QuizLab.exceptions.ResourceNotFoundException;
import Backend.QuizLab.models.survey.SurveyOption;
import Backend.QuizLab.repositories.survey.SurveyOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SurveyOptionService {
    @Autowired
    private SurveyOptionRepository surveyOptionRepository;

    public SurveyOption saveSurveyOption (SurveyOption surveyOption){
        return surveyOptionRepository.save(surveyOption);
    }

    public  SurveyOption getById ( long id ){
        return surveyOptionRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Option not found"));
    }


}
