//package Backend.QuizLab.repositories.quiz;
//
//import Backend.QuizLab.models.quiz.QuizAttempt;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.ActiveProfiles;
//
//@DataJpaTest
//@ActiveProfiles("test")
//public class QuizAttemptRepositoryTest {
//
//    @Autowired
//    private QuizAttemptRepository quizAttemptRepository;
//
//    @Test
//    public void QuizAttemptRepository_SaveSingleEntity_ReturnSavedEntity ()
//    {
//        // Arrange
//        var attempt = new QuizAttempt();
//
//        // Act
//        var savedAttempt = quizAttemptRepository.save(attempt);
//
//        // Assert
//        Assertions.assertThat(savedAttempt).isNotNull();
//        Assertions.assertThat(savedAttempt.getId()).isGreaterThan(0);
//    }
//}
