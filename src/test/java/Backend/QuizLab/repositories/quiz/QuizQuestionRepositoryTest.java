package Backend.QuizLab.repositories.quiz;

import Backend.QuizLab.models.quiz.QuizOption;
import Backend.QuizLab.models.quiz.QuizQuestion;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
public class QuizQuestionRepositoryTest {

    @Autowired
    private QuizQuestionRepository quizQuestionRepository;

    @Test
    public void QuizQuestionRepository_SaveSingleEntity_ReturnSavedEntity ()
    {
        //  Arrange
        List<QuizOption> options = new ArrayList<>();
        var quizQuestion = new QuizQuestion("Sample Question", "This is a sample question.", true, 1.0, null, options);

        //  Act
        var savedQuizQuestion = quizQuestionRepository.save(quizQuestion);

        //  Assert
        Assertions.assertThat(savedQuizQuestion).isNotNull();
        Assertions.assertThat(savedQuizQuestion.getId()).isGreaterThan(0L);

    }

    @Test
    public void QuizQuestionRepository_SaveMultipleEntities_ReturnSavedEntities ()
    {
        //  Arrange
        List<QuizOption> options = new ArrayList<>();
        var quizQuestion1 = new QuizQuestion("Sample Question 1", "This is the first sample question.", true, 1.0, null, options);
        var quizQuestion2 = new QuizQuestion("Sample Question 2", "This is the second sample question.", true, 1.0, null,  options);

        //  Act
        quizQuestionRepository.save(quizQuestion1);
        quizQuestionRepository.save(quizQuestion2);
        var quizQuestions = quizQuestionRepository.findAll();

        //  Assert
        Assertions.assertThat(quizQuestions).isNotNull();
        Assertions.assertThat(quizQuestions.size()).isEqualTo(2);
    }
}
