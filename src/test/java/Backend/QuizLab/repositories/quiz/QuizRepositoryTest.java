//package Backend.QuizLab.repositories.quiz;
//
//import Backend.QuizLab.models.quiz.Quiz;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.ActiveProfiles;
//
//@DataJpaTest
//@ActiveProfiles("test")
//public class QuizRepositoryTest {
//
//    @Autowired
//    private QuizRepository quizRepository;
//
//
//    @Test
//    public void QuizRepository_SaveSingleEntity_ReturnSavedEntity (){
//        //  Arrange
//        var quiz = new Quiz();
//        quiz.setTitle("Test Quiz");
//
//        //  Act
//        var savedQuiz = quizRepository.save(quiz);
//
//        //  Assert
//        Assertions.assertThat(savedQuiz).isNotNull();
//        Assertions.assertThat(savedQuiz.getId()).isGreaterThan(0L);
//    }
//
//    @Test
//    public void QuizRepository_SaveMultipleEntities_ReturnSavedEntities (){
//        //  Arrange
//        var quiz1 = new Quiz();
//        quiz1.setTitle("Test Quiz 1");
//        var quiz2 = new Quiz();
//        quiz2.setTitle("Test Quiz 2");
//
//        //  Act
//        quizRepository.save(quiz1);
//        quizRepository.save(quiz2);
//        var quizzes = quizRepository.findAll();
//
//        //  Assert
//        Assertions.assertThat(quizzes).isNotNull();
//        Assertions.assertThat(quizzes.size()).isEqualTo(2);
//    }
//}
