package Backend.QuizLab.dtos.llm.quiz;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import java.util.List;
import java.util.Optional;

@JsonClassDescription("the quiz question")
public class Question {
    @JsonPropertyDescription("Question statement")
    public String statement;
    @JsonPropertyDescription("The correct answers to the question")
    public List<String> correct_options;
    @JsonPropertyDescription("The wrong answers to question")
    public List<String> other_options;
    @JsonPropertyDescription("Optional explanation shown after answering; up to 1000 characters; Markdown allowed.")
    public Optional <String> explanation;
}
