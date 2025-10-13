package Backend.QuizLab.dtos.llm.quiz;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import java.util.List;

@JsonClassDescription("Quiz metadata and an ordered list of questions.")
public class Quiz {
    @JsonPropertyDescription("Title shown to users; 1 to 100 characters; used in listings and page headers.")
    public String title;
    @JsonPropertyDescription("Summary of purpose and topic; Markdown allowed; 10 to 500 characters; displayed on the quiz start screen.")
    public String description;
    @JsonPropertyDescription("Ordered list of questions; 1 to 50 items; order defines rendering.")
    public List<Question> questions;
}
