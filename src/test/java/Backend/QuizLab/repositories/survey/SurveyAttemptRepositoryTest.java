//package Backend.QuizLab.repositories.survey;
//
//
//import Backend.QuizLab.models.survey.SurveyAttempt;
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
//public class SurveyAttemptRepositoryTest {
//
//    @Autowired
//    private SurveyAttemptRepository surveyAttemptRepository;
//
//    @Test
//    public void SurveyAttemptRepository_SaveSingleEntity_ReturnSavedEntity() {
//        // Arrange
//        var surveyAttempt = new SurveyAttempt();
//
//        // Act
//        var savedAttempt = surveyAttemptRepository.save(surveyAttempt);
//
//        // Assert
//        Assertions.assertThat(savedAttempt).isNotNull();
//        Assertions.assertThat(savedAttempt.getId()).isGreaterThan(0);
//    }
//
//    @Test
//    public void SurveyAttemptRepository_SaveMultipleEntities_ReturnSavedEntities() {
//        // Arrange
//        var surveyAttempt1 = new SurveyAttempt();
//        var surveyAttempt2 = new SurveyAttempt();
//
//        // Act
//        var attempts = surveyAttemptRepository.saveAll(List.of(surveyAttempt1, surveyAttempt2));
//
//        // Assert
//        Assertions.assertThat(attempts).isNotNull();
//        Assertions.assertThat(attempts.size()).isEqualTo(2);
//    }
//}
