package Backend.QuizLab.controllers;

import Backend.QuizLab.models.quiz.Quiz;
import Backend.QuizLab.services.quiz.QuizService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/quizzes")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping
    public ResponseEntity<Quiz> createQuiz (@Valid @RequestBody Quiz quiz) {
        var createdQuiz = quizService.create(quiz);
        return new ResponseEntity<>(createdQuiz, HttpStatus.CREATED);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<Quiz> getQuiz (@PathVariable long id){
        var quiz = quizService.getById(id);
        return  new ResponseEntity<>(quiz,HttpStatus.FOUND);
    }
}
