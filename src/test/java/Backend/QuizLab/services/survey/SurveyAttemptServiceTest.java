//package Backend.QuizLab.services.survey;
//
//import Backend.QuizLab.exceptions.ResourceNotFoundException;
//import Backend.QuizLab.models.survey.SurveyAnswerRecord;
//import Backend.QuizLab.models.survey.SurveyAttempt;
//import Backend.QuizLab.repositories.survey.SurveyAttemptRepository;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class SurveyAttemptServiceTest {
//
//    @Mock
//    private SurveyAttemptRepository attemptRepository;
//    @Mock
//    private SurveyAnswerRecordService answerRecordService;
//    @InjectMocks
//    private SurveyAttemptService attemptService;
//
//    @Test
//    public void SurveyAttemptService_CreateSingleEntity_ReturnEntity(){
//        // Arrange
//        var attempt = new SurveyAttempt();
//        var answer1 = new SurveyAnswerRecord();
//        var answer2 = new SurveyAnswerRecord();
//        attempt.setId(1L); answer1.setId(1L); answer2.setId(2L);
//        attempt.setAnswers(List.of(answer2,answer1));
//        when(attemptRepository.save(attempt)).thenReturn(attempt);
//        when(answerRecordService.create(answer1)).thenReturn(answer1);
//        when(answerRecordService.create(answer2)).thenReturn(answer2);
//
//        // Act
//        var returnedAttempt = attemptService.create(attempt);
//
//        // Assert
//        assert returnedAttempt != null ;
//        assert attempt.getId() == attempt.getAnswers().get(0).getSurveyAttempt().getId();
//        verify(attemptRepository,times(1)).save(attempt);
//        verify(answerRecordService,times(2)).create(any(SurveyAnswerRecord.class));
//    }
//
//    @Test
//    public void SurveyAttemptService_GetEntityById_ReturnEntity(){
//        // Arrange
//        var attempt = new SurveyAttempt();
//        attempt.setId(1L);
//        when(attemptRepository.findById(1L)).thenReturn(Optional.of(attempt));
//
//        // Act
//        var returnedAttempt = attemptService.getById(1L);
//
//        // Assert
//        assert returnedAttempt != null;
//        verify(attemptRepository,times(1)).findById(1L);
//    }
//
//    @Test
//    public void SurveyAttemptService_GetEntityById_NoneExistingEntity(){
//        // Arrange
//        when(attemptRepository.findById(1L)).thenReturn(Optional.empty());
//
//        // Act & Assert
//        Assertions.assertThrows(ResourceNotFoundException.class,()-> attemptService.getById(1L));
//        verify(attemptRepository,times(1)).findById(1L);
//    }
//}
