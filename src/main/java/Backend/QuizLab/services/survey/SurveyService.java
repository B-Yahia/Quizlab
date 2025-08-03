package Backend.QuizLab.services.survey;

import Backend.QuizLab.models.survey.Survey;
import Backend.QuizLab.models.survey.SurveyQuestion;
import Backend.QuizLab.repositories.survey.SurveyRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SurveyService {

    @Autowired
    private SurveyRepository surveyRepository;
    @Autowired
    private SurveyQuestionService surveyQuestionService;

    @Transactional
    public Survey create (Survey survey){
        var newSurvey = surveyRepository.save(survey);
        List<SurveyQuestion> questions = new ArrayList<>();
        for (SurveyQuestion question : survey.getQuestions()){
            question.setSurvey(newSurvey);
            questions.add(surveyQuestionService.create(question));
        }
        newSurvey.setQuestions(questions);
        return newSurvey;
    }
}
