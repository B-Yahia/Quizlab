package Backend.QuizLab.controllers;

import Backend.QuizLab.dtos.quiz.QuizAttemptDTO;
import Backend.QuizLab.mapper.quiz.QuizAttemptMapper;
import Backend.QuizLab.models.quiz.QuizAttempt;
import Backend.QuizLab.services.quiz.QuizAttemptService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/quiz_attempts")
public class QuizAttemptController {

    @Autowired
    private QuizAttemptService attemptService;
    @Autowired
    private QuizAttemptMapper mapper;

    @PostMapping
    public ResponseEntity<QuizAttemptDTO> createQuizAttempt (@Valid @RequestBody QuizAttemptDTO dto){
        var attempt = attemptService.create(mapper.toEntity(dto));
        return new ResponseEntity<>(mapper.toDTO(attempt), HttpStatus.CREATED);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<QuizAttempt> getQuizAttempt (@PathVariable long id){
        var attempt = attemptService.getById(id);
        return new ResponseEntity<>(attempt, HttpStatus.FOUND);
    }
}
