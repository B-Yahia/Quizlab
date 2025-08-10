package Backend.QuizLab.services.quiz;

import Backend.QuizLab.exceptions.ResourceNotFoundException;
import Backend.QuizLab.models.quiz.QuizAnswerRecord;
import Backend.QuizLab.models.quiz.QuizAttempt;
import Backend.QuizLab.repositories.quiz.QuizAttemptRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class QuizAttemptServiceTest {

    @Mock
    private QuizAttemptRepository attemptRepository;
    @Mock
    private QuizAnswerRecordService answerRecordService;
    @Mock
    private QuizGradingService gradingService;
    @InjectMocks
    private QuizAttemptService attemptService;

    @Test
    public void QuizAttemptService_CreateEntity_ReturnEntity (){

        // Arrange
        var attempt = new QuizAttempt(); attempt.setId(1L);
        var record = new QuizAnswerRecord(); record.setId(2L);
        attempt.setAnswerRecords(List.of(record));
        when(gradingService.gradeQuizAttempt(attempt)).thenReturn(attempt);
        when(attemptRepository.save(attempt)).thenReturn(attempt);
        when(answerRecordService.save(record)).thenReturn(record);

        // Act
        var returnedAttempt = attemptService.create(attempt);

        // Assert
        assert returnedAttempt != null;
        assert returnedAttempt.getAnswerRecords().get(0).getQuizAttempt().getId() == attempt.getId();
        verify(gradingService,times(1)).gradeQuizAttempt(attempt);
        verify(attemptRepository,times(1)).save(attempt);
        verify(answerRecordService,times(1)).save(record);
    }

    @Test
    public void QuizAttemptService_GetEntityById_ReturnEntity (){
        // Arrange
        var attempt = new QuizAttempt(); attempt.setId(1L);
        when(attemptRepository.findById(1L)).thenReturn(Optional.of(attempt));

        // Act
        var returnedAttempt = attemptService.getById(1L);

        // Assert
        assert returnedAttempt != null;
        assert returnedAttempt.getId() == attempt.getId();
        verify(attemptRepository,times(1)).findById(anyLong());
    }

    @Test
    public void QuizAttemptService_GetEntityById_NoneExistingEntity (){
        // Arrange
        when(attemptRepository.findById(1l)).thenReturn(Optional.empty());

        // Assert
        Assertions.assertThrows(ResourceNotFoundException.class,()-> attemptService.getById(1L));
        verify(attemptRepository,times(1)).findById(1L);

    }

}
