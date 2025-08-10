package Backend.QuizLab.controllers;

import Backend.QuizLab.models.survey.SurveyAttempt;
import Backend.QuizLab.services.survey.SurveyAttemptService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/survey_attempts")
public class SurveyAttemptController {

    @Autowired
    private SurveyAttemptService attemptService;

    @PostMapping
    public ResponseEntity<SurveyAttempt> createSurveyAttempt (@Valid @RequestBody SurveyAttempt surveyAttempt){
        var createdAttempt = attemptService.create(surveyAttempt);
        return new ResponseEntity<>(createdAttempt,HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SurveyAttempt> getSurveyAttempt (@PathVariable long id){
        var attempt = attemptService.getById(id);
        return new ResponseEntity<>(attempt,HttpStatus.FOUND);
    }

}
