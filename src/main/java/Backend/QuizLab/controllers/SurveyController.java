package Backend.QuizLab.controllers;

import Backend.QuizLab.dtos.llm.quiz.Quiz;
import Backend.QuizLab.dtos.llm.quiz.QuizRequest;
import Backend.QuizLab.dtos.llm.survey.Survey;
import Backend.QuizLab.dtos.llm.survey.SurveyRequest;
import Backend.QuizLab.dtos.survey.SurveyDTO;
import Backend.QuizLab.mapper.survey.SurveyMapper;
import Backend.QuizLab.services.ai.OpenAIService;
import Backend.QuizLab.services.survey.SurveyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/surveys")
public class SurveyController {

    @Autowired
    private SurveyService surveyService;
    @Autowired
    private SurveyMapper mapper;
    @Autowired
    private OpenAIService openAIService;

    @PostMapping
    public ResponseEntity<SurveyDTO> createSurvey (@Valid @RequestBody SurveyDTO dto){
        var createdSurvey = surveyService.create(mapper.toEntity(dto));
        return new ResponseEntity<>(mapper.toDTO(createdSurvey), HttpStatus.CREATED);
    }

    @PostMapping("/ai")
    public  ResponseEntity<Survey> createSurvey (@Valid @RequestBody SurveyRequest request){
        var survey = openAIService.createSurvey(request.userPrompt,request.numberOfQuestions);
        return new ResponseEntity<>(survey,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SurveyDTO> getSurvey (@PathVariable UUID id){
        var survey = surveyService.getById(id);
        return new ResponseEntity<>(mapper.toDTO(survey),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<SurveyDTO>> getAllSurveys (){
        return new ResponseEntity<>(mapper.toDTOs(surveyService.getAll()),HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<SurveyDTO>> getAllUserSurveys (@PathVariable UUID id){
        return new ResponseEntity<>(mapper.toDTOs(surveyService.getAllByCreatorId(id)),HttpStatus.OK);
    }
}
