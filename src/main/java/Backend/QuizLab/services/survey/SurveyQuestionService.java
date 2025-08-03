package Backend.QuizLab.services.survey;

import Backend.QuizLab.exceptions.ResourceNotFoundException;
import Backend.QuizLab.models.survey.SurveyOption;
import Backend.QuizLab.models.survey.SurveyQuestion;
import Backend.QuizLab.repositories.survey.SurveyQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SurveyQuestionService {

    @Autowired
    private SurveyQuestionRepository surveyQuestionRepository;
    @Autowired
    private SurveyOptionService surveyOptionService;

    public SurveyQuestion create ( SurveyQuestion surveyQuestion){
        var question = surveyQuestionRepository.save(surveyQuestion);
        List<SurveyOption> options = new ArrayList<>();
        for (SurveyOption option : question.getOptions()) {
            option.setQuestion(question);
            options.add(surveyOptionService.save(option));
        }
        question.setOptions(options);
        return question;
    }

    public  SurveyQuestion getById (long id){
        return surveyQuestionRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Question with the ID "+id+" can not be found"));
    }
}
