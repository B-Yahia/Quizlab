package Backend.QuizLab.repositories.quiz;

import Backend.QuizLab.models.quiz.QuizAnswerRecord;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class QuizAnswerRecordRepositoryTest {

    @Autowired
    private QuizAnswerRecordRepository quizAnswerRecordRepository;

    @Test
    public void QuizAnswerRecordRepository_SaveSingleEntity_ReturnSavedEntity (){
        //  Arrange
        var quizAnswerRecord = new QuizAnswerRecord();

        //  Act
        var savedQuizAnswerRecord = quizAnswerRecordRepository.save(quizAnswerRecord);

        //  Assert
        Assertions.assertThat(savedQuizAnswerRecord).isNotNull();
        Assertions.assertThat(savedQuizAnswerRecord.getId()).isGreaterThan(0);
    }

    @Test
    public void QuizAnswerRecordRepository_SaveMultipleEntities_ReturnSavedEntities (){
        //  Arrange
        var quizAnswerRecord1 = new QuizAnswerRecord();
        var quizAnswerRecord2 = new QuizAnswerRecord();

        //  Act
        quizAnswerRecordRepository.save(quizAnswerRecord1);
        quizAnswerRecordRepository.save(quizAnswerRecord2);
        var quizAnswerRecords = quizAnswerRecordRepository.findAll();

        //  Assert
        Assertions.assertThat(quizAnswerRecords).isNotNull();
        Assertions.assertThat(quizAnswerRecords.size()).isEqualTo(2);
    }

}
