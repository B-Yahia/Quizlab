package Backend.QuizLab.controllers;

import Backend.QuizLab.dtos.survey.SurveyDTO;
import Backend.QuizLab.mapper.survey.SurveyMapper;
import Backend.QuizLab.services.survey.SurveyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/surveys")
public class SurveyController {

    @Autowired
    private SurveyService surveyService;
    @Autowired
    private SurveyMapper mapper;

    @PostMapping
    public ResponseEntity<SurveyDTO> createSurvey (@Valid @RequestBody SurveyDTO dto){
        var createdSurvey = surveyService.create(mapper.toEntity(dto));
        return new ResponseEntity<>(mapper.toDTO(createdSurvey), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SurveyDTO> getSurvey (@PathVariable long id){
        var survey = surveyService.getById(id);
        return new ResponseEntity<>(mapper.toDTO(survey),HttpStatus.FOUND);
    }

    @GetMapping
    public ResponseEntity<List<SurveyDTO>> getAllSurveys (){
        return new ResponseEntity<>(mapper.toDTOs(surveyService.getAll()),HttpStatus.FOUND);
    }
}
