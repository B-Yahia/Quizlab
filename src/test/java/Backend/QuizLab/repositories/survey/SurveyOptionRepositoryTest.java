package Backend.QuizLab.repositories.survey;


import Backend.QuizLab.models.survey.SurveyOption;
import Backend.QuizLab.models.survey.SurveyQuestion;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
public class SurveyOptionRepositoryTest {

    @Autowired
    private SurveyOptionRepository surveyOptionRepository;

    @Test
    public void SurveyOptionRepository_SaveSingleEntity_ReturnSavedEntity (){

        //Arrange
        var option = new SurveyOption();
        option.setStatement("Option 1");

        //Act
        SurveyOption savedOption = surveyOptionRepository.save(option);

        //Assert
        Assertions.assertThat(savedOption).isNotNull();
        Assertions.assertThat(savedOption.getId()).isGreaterThan(0);

    }

    @Test
    public void SurveyOptionRepository_SaveMultipleEntities_ReturnSavedEntities (){
        //Arrange
        var option1 = new SurveyOption();
        option1.setStatement("Option 1");
        var option2 = new SurveyOption();
        option2.setStatement("Option 2");

        //Act
        surveyOptionRepository.saveAll(List.of(option1, option2));
        List<SurveyOption> savedOptions = surveyOptionRepository.findAll();
        Assertions.assertThat(savedOptions).isNotNull();
        Assertions.assertThat(savedOptions.size()).isEqualTo(2);


    }

}
