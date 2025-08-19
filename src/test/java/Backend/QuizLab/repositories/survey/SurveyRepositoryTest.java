//package Backend.QuizLab.repositories.survey;
//
//
//import Backend.QuizLab.models.survey.Survey;
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
//public class SurveyRepositoryTest {
//
//    @Autowired
//    private SurveyRepository surveyRepository;
//
//    @Test
//    public void SurveyRepository_SaveSingleEntity_ReturnSavedEntity ()
//    {
//        //Arrange
//        var survey = new Survey();
//        survey.setTitle("Survey Title");
//
//        //Act
//        var savedSurvey = surveyRepository.save(survey);
//
//        //Assert
//        Assertions.assertThat(savedSurvey).isNotNull();
//        Assertions.assertThat(savedSurvey.getId()).isGreaterThan(0);
//    }
//
//    @Test
//    public void SurveyRepository_SaveMultipleEntities_ReturnSavedEntities ()
//    {
//        //Arrange
//        var survey1 = new Survey();
//        survey1.setTitle("Survey Title 1");
//        var survey2 = new Survey();
//        survey2.setTitle("Survey Title 2");
//
//        //Act
//        surveyRepository.saveAll(List.of(survey1, survey2));
//        var savedSurveys = surveyRepository.findAll();
//
//        //Assert
//        Assertions.assertThat(savedSurveys).isNotNull();
//        Assertions.assertThat(savedSurveys.size()).isEqualTo(2);
//    }
//}
