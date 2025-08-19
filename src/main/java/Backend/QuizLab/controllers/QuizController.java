package Backend.QuizLab.controllers;

import Backend.QuizLab.dtos.quiz.QuizDTO;
import Backend.QuizLab.mapper.quiz.QuizMapper;
import Backend.QuizLab.services.quiz.QuizQuestionService;
import Backend.QuizLab.services.quiz.QuizService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/quizzes")
public class QuizController {

    @Autowired
    private QuizService quizService;
    @Autowired
    private QuizMapper mapper;
    @Autowired
    private QuizQuestionService ss;


    @PostMapping
    public ResponseEntity<QuizDTO> createQuiz (@Valid @RequestBody QuizDTO dto) {
        return new ResponseEntity<>(mapper.toDTO(quizService.create(mapper.toEntity(dto))), HttpStatus.CREATED);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<QuizDTO> getQuiz (@PathVariable long id){
        return  new ResponseEntity<>(mapper.toDTO(quizService.getById(id)),HttpStatus.FOUND);
    }

    @GetMapping
    public ResponseEntity<List<QuizDTO>> getAllQuizzes (){
        return new ResponseEntity<>(mapper.toDTOs(quizService.getAll()),HttpStatus.FOUND);
    }
    @GetMapping("/test")
    public ResponseEntity<?> test (){
        return  new ResponseEntity<>(ss.getById(1L),HttpStatus.FOUND);
    }
}
