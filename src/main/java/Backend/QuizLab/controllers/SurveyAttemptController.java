package Backend.QuizLab.controllers;

import Backend.QuizLab.dtos.survey.SurveyAttemptDTO;
import Backend.QuizLab.mapper.survey.SurveyAttemptMapper;
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
    @Autowired
    private SurveyAttemptMapper mapper;

    @PostMapping
    public ResponseEntity<SurveyAttemptDTO> createSurveyAttempt (@Valid @RequestBody SurveyAttemptDTO dto){
        var entity = attemptService.create(mapper.toEntity(dto));
        return new ResponseEntity<>(mapper.toDTO(entity),HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SurveyAttemptDTO> getSurveyAttempt (@PathVariable long id){
        var attempt = attemptService.getById(id);
        return new ResponseEntity<>(mapper.toDTO(attempt),HttpStatus.FOUND);
    }

}
