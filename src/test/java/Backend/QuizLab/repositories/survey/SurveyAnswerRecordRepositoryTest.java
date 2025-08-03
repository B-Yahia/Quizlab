package Backend.QuizLab.repositories.survey;

import Backend.QuizLab.models.survey.SurveyAnswerRecord;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;


@DataJpaTest
@ActiveProfiles("test")
public class SurveyAnswerRecordRepositoryTest {

    @Autowired
    private SurveyAnswerRecordRepository surveyAnswerRecordRepository;

    @Test
    public void SurveyAnswerRecordRepository_SaveSingleEntity_ReturnSavedEntity ()
    {
        // Arrange
        var surveyAnswerRecord = new SurveyAnswerRecord();

        //Act
        var savedRecord = surveyAnswerRecordRepository.save(surveyAnswerRecord);

        // Assert
        Assertions.assertThat(savedRecord).isNotNull();
        Assertions.assertThat(savedRecord.getId()).isGreaterThan(0);
    }

    @Test
    public void SurveyAnswerRecordRepository_SaveMultipleEntities_ReturnSavedEntities ()
    {
        // Arrange
        var surveyAnswerRecord1 = new SurveyAnswerRecord();
        var surveyAnswerRecord2 = new SurveyAnswerRecord();

        // Act
        var records = surveyAnswerRecordRepository.saveAll(List.of(surveyAnswerRecord1, surveyAnswerRecord2));

        // Assert
        Assertions.assertThat(records).isNotNull();
        Assertions.assertThat(records.size()).isEqualTo(2);
    }
}
