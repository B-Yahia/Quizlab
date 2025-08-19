//package Backend.QuizLab.repositories.quiz;
//
//import Backend.QuizLab.models.quiz.QuizOption;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.ActiveProfiles;
//
//import java.util.List;
//
//@DataJpaTest
//@ActiveProfiles("test")
//public class QuizOptionRepositoryTest {
//
//    @Autowired
//    private QuizOptionRepository quizOptionRepository;
//
//    @Test
//    public void QuizOptionRepository_SaveSingleEntity_ReturnSavedEntity ()
//    {
//        // Arrange
//        QuizOption quizOption = new QuizOption();
//        quizOption.setStatement("Option 1");
//
//        //  Act
//        QuizOption savedQuizOption = quizOptionRepository.save(quizOption);
//
//        // Assert
//        Assertions.assertThat(savedQuizOption).isNotNull();
//        Assertions.assertThat(savedQuizOption.getId()).isGreaterThan(0L);
//    }
//
//    @Test
//    public void QuizOptionRepository_SaveMultipleEntities_ReturnSavedEntities ()
//    {
//        // Arrange
//        QuizOption quizOption1 = new QuizOption();
//        QuizOption quizOption2 = new QuizOption();
//        quizOption1.setStatement("Option 1");
//        quizOption2.setStatement("Option 2");
//
//        //  Act
//        quizOptionRepository.save(quizOption1);
//        quizOptionRepository.save(quizOption2);
//        List<QuizOption> quizOptions = quizOptionRepository.findAll();
//
//        // Assert
//        Assertions.assertThat(quizOptions).isNotNull();
//        Assertions.assertThat(quizOptions.size()).isEqualTo(2);
//    }
//}
