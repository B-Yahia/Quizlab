//package Backend.QuizLab.services.survey;
//
//import Backend.QuizLab.exceptions.ResourceNotFoundException;
//import Backend.QuizLab.models.survey.Survey;
//import Backend.QuizLab.models.survey.SurveyQuestion;
//import Backend.QuizLab.repositories.survey.SurveyRepository;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.jupiter.api.Test;
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
//public class SurveyServiceTest {
//
//    @Mock
//    private SurveyRepository surveyRepository;
//    @Mock
//    private SurveyQuestionService surveyQuestionService;
//    @InjectMocks
//    private SurveyService surveyService;
//
//    @Test
//    public void SurveyService_CreateSingleEntity_ReturnEntity(){
//
//        // Arrange
//        var survey = new Survey();
//        var question1 = new SurveyQuestion();
//        var question2 = new SurveyQuestion();
//        survey.setId(1L); question1.setId(1L); question2.setId(2L);
//        survey.setQuestions(List.of(question1,question2));
//        when(surveyRepository.save(survey)).thenReturn(survey);
//        when(surveyQuestionService.create(question1)).thenReturn(question1);
//        when(surveyQuestionService.create(question2)).thenReturn(question2);
//
//
//        // Act
//        var returnedSurvey = surveyService.create(survey);
//
//        // Assert
//        assert returnedSurvey != null;
//        assert survey.getQuestions().get(0).getSurvey().getId() == survey.getId();
//        verify(surveyQuestionService,times(2)).create(any(SurveyQuestion.class));
//        verify(surveyRepository,times(1)).save(survey);
//
//    }
//
//    @Test
//    public void SurveyService_GetById_ReturnEntity () {
//        // Arrange
//        var survey = new Survey();
//        survey.setId(1L);
//        when(surveyRepository.findById(1L)).thenReturn(Optional.of(survey));
//
//        // Act
//        var returnedSurvey = surveyService.getById(1L);
//
//        // Assert
//        assert returnedSurvey != null;
//        assert returnedSurvey.getId() == survey.getId();
//        verify(surveyRepository, times(1)).findById(anyLong());
//    }
//
//    @Test
//    public void SurveyService_GetById_NoneExistingEntity (){
//        // Arrange
//        var id = 1L;
//        when(surveyRepository.findById(id)).thenReturn(Optional.empty());
//
//        // Act & Assert
//        Assertions.assertThrows(ResourceNotFoundException.class, ()-> surveyService.getById(id));
//        verify(surveyRepository,times(1)).findById(anyLong());
//    }
//}
