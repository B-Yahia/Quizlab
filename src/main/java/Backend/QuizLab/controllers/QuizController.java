package Backend.QuizLab.controllers;

import Backend.QuizLab.dtos.llm.quiz.Quiz;
import Backend.QuizLab.dtos.llm.quiz.QuizRequest;
import Backend.QuizLab.dtos.quiz.QuizDTO;
import Backend.QuizLab.mapper.quiz.QuizMapper;
import Backend.QuizLab.services.ai.OpenAIService;
import Backend.QuizLab.services.quiz.QuizQuestionService;
import Backend.QuizLab.services.quiz.QuizService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/quizzes")
public class QuizController {

    @Autowired
    private QuizService quizService;
    @Autowired
    private QuizMapper mapper;
    @Autowired
    private OpenAIService openAIService;


    @PostMapping
    public ResponseEntity<QuizDTO> createQuiz (@Valid @RequestBody QuizDTO dto) {
        return new ResponseEntity<>(mapper.toDTO(quizService.create(mapper.toEntity(dto))), HttpStatus.CREATED);
    }

    @PostMapping("/ai")
    public  ResponseEntity<QuizDTO> createQuiz (@Valid @RequestBody QuizRequest request){
        var aiResponse = openAIService.createQuiz(request.userPrompt,request.numberOfQuestions);
        return new ResponseEntity<>(mapper.toDTO(aiResponse),HttpStatus.OK);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<QuizDTO> getQuiz (@PathVariable UUID id){
        return  new ResponseEntity<>(mapper.toDTO(quizService.getById(id)),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<QuizDTO>> getAllQuizzes (){
        return new ResponseEntity<>(mapper.toDTOs(quizService.getAll()),HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<QuizDTO>> getAllUserQuizzes ( @PathVariable UUID id ){
        return new ResponseEntity<>(mapper.toDTOs(quizService.getAllByCreatorId(id)),HttpStatus.OK);
    }
}
