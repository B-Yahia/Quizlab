package Backend.QuizLab.controllers;

import Backend.QuizLab.dtos.survey.SurveyAttemptDTO;
import Backend.QuizLab.dtos.survey.SurveyAttemptResponse;
import Backend.QuizLab.mapper.survey.SurveyAttemptMapper;
import Backend.QuizLab.mapper.survey.SurveyAttemptResponseMapper;
import Backend.QuizLab.services.survey.SurveyAttemptService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/survey_attempts")
public class SurveyAttemptController {

    @Autowired
    private SurveyAttemptService attemptService;
    @Autowired
    private SurveyAttemptMapper mapper;
    @Autowired
    private SurveyAttemptResponseMapper resultMapper;

    @PostMapping
    public ResponseEntity<SurveyAttemptDTO> createSurveyAttempt (@Valid @RequestBody SurveyAttemptDTO dto){
        var entity = attemptService.create(mapper.toEntity(dto));
        return new ResponseEntity<>(mapper.toDTO(entity),HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SurveyAttemptResponse> getSurveyAttempt (@PathVariable UUID id){
        var attempt = attemptService.getById(id);
        return new ResponseEntity<>(resultMapper.toResponse(attempt),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<SurveyAttemptResponse>> getAllAttempts (){
        return new ResponseEntity<>(resultMapper.toResponses(attemptService.getAll()),HttpStatus.OK);
    }

    @GetMapping("/survey/{id}")
    public ResponseEntity<List<SurveyAttemptResponse>> getAllAttemptsBySurveyId(@PathVariable UUID id){
        var list = attemptService.getAllBySurveyId(id);
        return new ResponseEntity<>(resultMapper.toResponses(list),HttpStatus.OK);
    }

}
