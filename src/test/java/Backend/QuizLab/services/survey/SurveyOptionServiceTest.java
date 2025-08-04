package Backend.QuizLab.services.survey;


import Backend.QuizLab.exceptions.ResourceNotFoundException;
import Backend.QuizLab.models.survey.SurveyOption;
import Backend.QuizLab.repositories.survey.SurveyOptionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SurveyOptionServiceTest {

    @Mock
    private SurveyOptionRepository surveyOptionRepository;

    @InjectMocks
    private SurveyOptionService surveyOptionService;

    @Test
    public void SurveyOptionService_SaveSingleEntity_ReturnSavedEntity ()
    {
        //  Arrange
        var surveyOption = new SurveyOption("Option 1", null);
        when(surveyOptionRepository.save(surveyOption)).thenReturn(surveyOption);

        //  Act
        var savedSurveyOption = surveyOptionService.save(surveyOption);

        //  Assert
        assert savedSurveyOption  != null;
        assert savedSurveyOption.getStatement().equals("Option 1");
    }

    @Test
    public void SurveyOptionService_GetById_ReturnEntity ()
    {
        //  Arrange
        var surveyOption = new SurveyOption("Option 1", null);
        surveyOption.setId(1L);
        when(surveyOptionRepository.findById(1L)).thenReturn(java.util.Optional.of(surveyOption));

        //  Act
        var foundSurveyOption = surveyOptionService.getById(1L);

        //  Assert
        assert foundSurveyOption != null;
        assert foundSurveyOption.getStatement().equals("Option 1");
    }

    @Test
    public void SurveyOptionService_GetById_ThrowExceptionForNoneValidId ()
    {
        //  Arrange
        when(surveyOptionRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        //  Act & Assert
        Assertions.assertThrows(ResourceNotFoundException.class, () -> surveyOptionService.getById(1L));
        verify(surveyOptionRepository,times(1)).findById(1L);
    }

}
