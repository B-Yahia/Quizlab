package Backend.QuizLab.models;

import Backend.QuizLab.models.commun.QuestionType;
import Backend.QuizLab.models.quiz.Quiz;
import Backend.QuizLab.models.quiz.QuizOption;
import Backend.QuizLab.models.quiz.QuizQuestion;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class QuizQuestionTest {

    @Test
    public void QuizQuestion_QuestionType_ShouldBeText_WhenNoOptionsAreProvided() {
        // Arrange + Act
        QuizQuestion  question = new QuizQuestion("Question", true, new Quiz());

        // Assert
        Assertions.assertThat(question.getQuestionType()).isEqualTo(QuestionType.TEXT);
    }

    @Test
    public void QuizQuestion_QuestionType_ShouldBeCheckbox_When1OptionIsProvided() {
        //  Arrange
        List<QuizOption> options = List.of(new QuizOption("Option 1", true, new QuizQuestion()), new QuizOption("Option 2", false, new QuizQuestion()));

        //  Act
        QuizQuestion  question = new QuizQuestion("Question", true, new Quiz(), options);

        //  Assert
        Assertions.assertThat(question.getQuestionType()).isEqualTo(QuestionType.CHECKBOX);
    }

    @Test
    public void QuizQuestion_QuestionType_ShouldBeMultipleChoice_WhenMultipleTrueOptionsAreProvided() {
        //  Arrange
        List<QuizOption> options = List.of(new QuizOption("Option 1", true, new QuizQuestion()), new QuizOption("Option 2", true, new QuizQuestion()));

        //  Act
        QuizQuestion  question = new QuizQuestion("Question", true, new Quiz(), options);

        // Assert
        Assertions.assertThat(question.getQuestionType()).isEqualTo(QuestionType.MULTIPLE_CHOICE);
    }
}
