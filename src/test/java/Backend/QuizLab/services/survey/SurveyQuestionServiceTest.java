package Backend.QuizLab.services.survey;

import Backend.QuizLab.exceptions.ResourceNotFoundException;
import Backend.QuizLab.models.survey.SurveyOption;
import Backend.QuizLab.models.survey.SurveyQuestion;
import Backend.QuizLab.repositories.survey.SurveyQuestionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SurveyQuestionServiceTest {

    @Mock
    private SurveyQuestionRepository surveyQuestionRepository;

    @Mock
    private SurveyOptionService surveyOptionService;

    @InjectMocks
    private SurveyQuestionService surveyQuestionService;

    @Test
    public void SurveyQuestionService_CreateSingleEntity_ReturnSavedEntity (){

        //  Arrange
         var surveyQuestion = new SurveyQuestion();
         surveyQuestion.setId(1L);
         var surveyOption1= new SurveyOption();
         var surveyOption2= new SurveyOption();

         surveyQuestion.setOptions(List.of(surveyOption1,surveyOption2));

         when(surveyQuestionRepository.save(surveyQuestion)).thenReturn(surveyQuestion);
         when(surveyOptionService.save(surveyOption1)).thenReturn(surveyOption1);
         when(surveyOptionService.save(surveyOption2)).thenReturn(surveyOption2);

        //  Act
         var savedSurveyQuestion = surveyQuestionService.create(surveyQuestion);

        //  Assert
         assert savedSurveyQuestion != null;
         assert savedSurveyQuestion.getOptions().size() == 2;
         assert Objects.equals(savedSurveyQuestion.getId(), savedSurveyQuestion.getOptions().get(0).getQuestion().getId());

         verify(surveyQuestionRepository,times(1)).save(surveyQuestion);
         verify(surveyOptionService,times(2)).save(any(SurveyOption.class));
    }

    @Test
    public void SurveyQuestionService_GetById_ReturnEntity (){
        //  Arrange
        var surveyQuestion = new SurveyQuestion();
        surveyQuestion.setId(1L);
        when(surveyQuestionRepository.findById(1L)).thenReturn(Optional.of(surveyQuestion));

        //  Act
        var foundSurveyQuestion = surveyQuestionService.getById(1L);

        //  Assert
        assert foundSurveyQuestion != null;
        assert Objects.equals(foundSurveyQuestion.getId(), surveyQuestion.getId());
        verify(surveyQuestionRepository,times(1)).findById(1L);

    }

    @Test
    public void SurveyQuestionService_GetById_NonExistingEntity (){
        // Arrange
        long id = 1;
        when(surveyQuestionRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        Assertions.assertThrows(ResourceNotFoundException.class,()->surveyQuestionService.getById(id));
        verify(surveyQuestionRepository,times(1)).findById(id);
    }


}
