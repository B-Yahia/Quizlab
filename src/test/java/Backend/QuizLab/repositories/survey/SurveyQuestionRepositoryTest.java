//package Backend.QuizLab.repositories.survey;
//
//import Backend.QuizLab.models.commun.QuestionType;
//import Backend.QuizLab.models.survey.SurveyQuestion;
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
//public class SurveyQuestionRepositoryTest {
//
//    @Autowired
//    private SurveyQuestionRepository surveyQuestionRepository;
//
//    @Test
//    public void SurveyQuestionService_SaveSingleEntity_ReturnSavedEntity ()
//    {
//        // Arrange
//        var question = new SurveyQuestion("Question1?", true, null, QuestionType.TEXT);
//
//        //  Act
//        var savedQuestion = surveyQuestionRepository.save(question);
//
//        // Assert
//        Assertions.assertThat(savedQuestion).isNotNull();
//        Assertions.assertThat(savedQuestion.getId()).isGreaterThan(0);
//    }
//
//    @Test
//    public void SurveyQuestionService_SaveMultipleEntities_ReturnSavedEntities ()
//    {
//        // Arrange
//        var question1 = new SurveyQuestion("Question1?", true, null, QuestionType.TEXT);
//        var question2 = new SurveyQuestion("Question2?", true, null, QuestionType.TEXT);
//
//        // Act
//        surveyQuestionRepository.saveAll(List.of(question1, question2));
//        var savedQuestions = surveyQuestionRepository.findAll();
//
//        // Assert
//        Assertions.assertThat(savedQuestions).isNotNull();
//        Assertions.assertThat(savedQuestions.size()).isEqualTo(2);
//    }
//}
