package Backend.QuizLab.controllers;

import Backend.QuizLab.services.ai.OpenAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private OpenAIService openAIService;

    @GetMapping("/chat")
    public ResponseEntity<?> chat() {
        var resp = openAIService.createQuiz("I want you to generate a quiz that will test my knowledge about geography",5);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
}