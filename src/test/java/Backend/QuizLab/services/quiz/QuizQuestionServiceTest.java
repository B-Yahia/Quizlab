package Backend.QuizLab.services.quiz;

import Backend.QuizLab.exceptions.ResourceNotFoundException;
import Backend.QuizLab.models.quiz.QuizOption;
import Backend.QuizLab.models.quiz.QuizQuestion;
import Backend.QuizLab.repositories.quiz.QuizQuestionRepository;
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
public class QuizQuestionServiceTest {

    @Mock
    private QuizQuestionRepository questionRepository;
    @Mock
    private QuizOptionService optionService;
    @InjectMocks
    private QuizQuestionService questionService;

    @Test
    public void QuizQuestionService_CreateEntity_ReturnEntity (){
        // Arrange
        var question = new QuizQuestion();question.setId(1l);
        var option1 = new QuizOption();
        var option2 = new QuizOption();
        question.setOptions(List.of(option1,option2));
        when(questionRepository.save(question)).thenReturn(question);
        when(optionService.create(option1)).thenReturn(option1);
        when(optionService.create(option2)).thenReturn(option2);

        // Act
        var returnedQuestion = questionService.create(question);

        // Assert
        assert returnedQuestion != null;
        assert returnedQuestion.getId() == question.getId();
        assert returnedQuestion.getOptions().get(0).getQuestion().getId() == question.getId();
        verify(optionService,times(2)).create(any(QuizOption.class));
        verify(questionRepository,times(1)).save(question);
    }

    @Test
    public void QuizQuestionService_GetEntityById_ReturnEntity (){
        // Arrange
        var question = new QuizQuestion();question.setId(1l);
        when(questionRepository.findById(1L)).thenReturn(Optional.of(question));

        // Act
        var returnedQuestion = questionService.getById(1L);

        // Assert
        assert returnedQuestion != null;
        assert returnedQuestion.getId() == question.getId();
        verify(questionRepository,times(1)).findById(1L);
    }

    @Test
    public void QuizQuestionService_GetEntityById_NoneExistingEntity (){
        // Arrange & Act
        when(questionRepository.findById(1L)).thenReturn(Optional.empty());

        // Assertions
        Assertions.assertThrows(ResourceNotFoundException.class,()-> questionService.getById(1L));
        verify(questionRepository,times(1)).findById(1L);
    }

}
