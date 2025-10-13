package Backend.QuizLab.controllers;

import Backend.QuizLab.dtos.quiz.QuizAttemptDTO;
import Backend.QuizLab.dtos.quiz.QuizAttemptResponse;
import Backend.QuizLab.mapper.quiz.QuizAttemptMapper;
import Backend.QuizLab.mapper.quiz.QuizAttemptResponseMapper;
import Backend.QuizLab.models.quiz.QuizAttempt;
import Backend.QuizLab.services.quiz.QuizAttemptService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/quiz_attempts")
public class QuizAttemptController {

    @Autowired
    private QuizAttemptService attemptService;
    @Autowired
    private QuizAttemptMapper mapper;
    @Autowired
    private QuizAttemptResponseMapper attemptResponseMapper;

    @PostMapping
    public ResponseEntity<QuizAttemptDTO> createQuizAttempt (@Valid @RequestBody QuizAttemptDTO dto){
        var attempt = attemptService.create(mapper.toEntity(dto));
        return new ResponseEntity<>(mapper.toDTO(attempt), HttpStatus.CREATED);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<QuizAttemptResponse> getQuizAttempt (@PathVariable UUID id){
        var entity = attemptService.getById(id);
        return new ResponseEntity<>(attemptResponseMapper.toResponse(entity), HttpStatus.OK);
    }

    @GetMapping ("/quiz/{id}")
    public ResponseEntity<List<QuizAttemptResponse>> getQuizAttemptsByQuizId(@PathVariable UUID id){
        var list = attemptService.getAllByQuizId(id);
        return new ResponseEntity<>(attemptResponseMapper.toResponses(list),HttpStatus.OK);
    }
}
