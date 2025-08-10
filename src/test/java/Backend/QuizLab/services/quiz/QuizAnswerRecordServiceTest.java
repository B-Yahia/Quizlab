package Backend.QuizLab.services.quiz;

import Backend.QuizLab.exceptions.ResourceNotFoundException;
import Backend.QuizLab.models.quiz.QuizAnswerRecord;
import Backend.QuizLab.repositories.quiz.QuizAnswerRecordRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class QuizAnswerRecordServiceTest {

    @InjectMocks
    private  QuizAnswerRecordService quizAnswerRecordService;

    @Mock
    private QuizAnswerRecordRepository quizAnswerRecordRepository;

    @Test
    public void QuizAnswerRecordService_CreateEntity_ReturnEntity (){
        // Arrange
        var answer = new QuizAnswerRecord();answer.setId(1L);
        when(quizAnswerRecordRepository.save(answer)).thenReturn(answer);

        // Act
        var returnedAnswer = quizAnswerRecordService.save(answer);

        // Assert
        assert returnedAnswer != null;
        assert returnedAnswer.getId() == answer.getId();
        verify(quizAnswerRecordRepository,times(1)).save(any(QuizAnswerRecord.class));
    }

    @Test
    public void QuizAnswerRecordService_GetEntityById_ReturnEntity (){
        // Arrange
        var answer = new QuizAnswerRecord();answer.setId(1L);
        when(quizAnswerRecordRepository.findById(1L)).thenReturn(Optional.of(answer));

        // Act
        var returnedAnswer = quizAnswerRecordService.getById(1L);

        // Assert
        assert returnedAnswer != null;
        assert returnedAnswer.getId() == answer.getId();
        verify(quizAnswerRecordRepository,times(1)).findById(1L);
    }

    @Test
    public void QuizAnswerRecordService_GetEntityById_NoneExistingEntity (){
        // Arrange & Act
        when( quizAnswerRecordRepository.findById(1L)).thenReturn(Optional.empty());

        // Assert
        Assertions.assertThrows(ResourceNotFoundException.class,()-> quizAnswerRecordService.getById(1L));
        verify(quizAnswerRecordRepository,times(1)).findById(1L);
    }

}
