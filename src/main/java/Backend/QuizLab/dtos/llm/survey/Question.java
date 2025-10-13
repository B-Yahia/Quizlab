package Backend.QuizLab.dtos.llm.survey;

import Backend.QuizLab.models.commun.QuestionType;

import java.util.List;
import java.util.Optional;

public class Question {
    public String statement;
    public List<String> options;
    public QuestionType type;
    public Optional<String> explanation;
}
