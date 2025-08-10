package Backend.QuizLab.controllers;

import Backend.QuizLab.models.survey.Survey;
import Backend.QuizLab.services.survey.SurveyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/surveys")
public class SurveyController {

    @Autowired
    private SurveyService surveyService;

    @PostMapping
    public ResponseEntity<Survey> createSurvey (@Valid @RequestBody Survey survey){
        var createdSurvey = surveyService.create(survey);
        return new ResponseEntity<>(createdSurvey, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Survey> getSurvey (@PathVariable long id){
        var survey = surveyService.getById(id);
        return new ResponseEntity<>(survey,HttpStatus.FOUND);
    }
}
