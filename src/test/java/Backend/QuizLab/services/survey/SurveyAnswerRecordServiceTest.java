package Backend.QuizLab.services.survey;

import Backend.QuizLab.exceptions.ResourceNotFoundException;
import Backend.QuizLab.models.survey.SurveyAnswerRecord;
import Backend.QuizLab.repositories.survey.SurveyAnswerRecordRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SurveyAnswerRecordServiceTest {

    @Mock
    private SurveyAnswerRecordRepository surveyAnswerRecordRepository;
    @InjectMocks
    private SurveyAnswerRecordService surveyAnswerRecordService;

    @Test
    public void SurveyAnswerRecordService_CreateSingleEntity_ReturnEntity(){
        // Arrange
        var answerRecord = new SurveyAnswerRecord();
        answerRecord.setId(1L);
        when(surveyAnswerRecordRepository.save(answerRecord)).thenReturn(answerRecord);

        // Act
        var returnerAnswer = surveyAnswerRecordService.create(answerRecord);

        // Assert
        assert returnerAnswer != null;
        assert returnerAnswer.getId() == answerRecord.getId();
        verify(surveyAnswerRecordRepository,times(1)).save(any(SurveyAnswerRecord.class));
    }

    @Test
    public void SurveyAnswerRecordService_GetById_ReturnEntity ()
    {
        //  Arrange
        var answerRecord = new SurveyAnswerRecord();
        answerRecord.setId(1L);
        when(surveyAnswerRecordRepository.findById(1L)).thenReturn(java.util.Optional.of(answerRecord));

        //  Act
        var foundSurveyAnswer = surveyAnswerRecordService.getById(1L);

        //  Assert
        assert foundSurveyAnswer != null;
        assert foundSurveyAnswer.getId() == answerRecord.getId();
        verify( surveyAnswerRecordRepository,times(1)).findById(anyLong());
    }

    @Test
    public void SurveyAnswerRecordService_GetById_NoneExistingEntity(){
        // Arrange
        var id = 1L;
        when(surveyAnswerRecordRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        Assertions.assertThrows(ResourceNotFoundException.class,()-> surveyAnswerRecordService.getById(id));
        verify(surveyAnswerRecordRepository,times(1)).findById(anyLong());
    }

}
