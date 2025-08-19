//package Backend.QuizLab.services.quiz;
//
//import Backend.QuizLab.exceptions.ResourceNotFoundException;
//import Backend.QuizLab.models.quiz.Quiz;
//import Backend.QuizLab.models.quiz.QuizQuestion;
//import Backend.QuizLab.repositories.quiz.QuizRepository;
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
//public class QuizServiceTest {
//
//    @Mock
//    private QuizRepository quizRepository;
//    @Mock
//    private QuizQuestionService questionService;
//    @InjectMocks
//    private QuizService quizService;
//
//    @Test
//    public void QuizService_CreateEntity_ReturnEntity(){
//        // Arrange
//        var quiz = new Quiz(); quiz.setId(1L);
//        var question1 = new QuizQuestion();
//        var question2 = new QuizQuestion();
//        quiz.setQuestions(List.of(question2,question1));
//        when(quizRepository.save(quiz)).thenReturn(quiz);
//        when(questionService.create(question1)).thenReturn(question1);
//        when(questionService.create(question2)).thenReturn(question2);
//
//        // Act
//        var returnedQuiz = quizService.create(quiz);
//
//        // Assert
//        assert returnedQuiz != null;
//        verify(quizRepository,times(1)).save(quiz);
//        verify(questionService,times(2)).create(any(QuizQuestion.class));
//    }
//
//    @Test
//    public void QuizService_GetEntityById_ReturnEntity(){
//        // Arrange
//        var quiz = new Quiz(); quiz.setId(1L);
//        when(quizRepository.findById(1L)).thenReturn(Optional.of(quiz));
//
//        // Act
//        var returnetQuiz = quizService.getById(1L);
//
//        // Assert
//        assert returnetQuiz != null;
//        assert returnetQuiz.getId() == quiz.getId();
//        verify(quizRepository,times(1)).findById(1L);
//    }
//
//    @Test
//    public void QuizService_GetEntityById_NoneExistingEntity(){
//        // Arrange & Act
//        when(quizRepository.findById(1L)).thenReturn(Optional.empty());
//
//        // Assertions
//        Assertions.assertThrows(ResourceNotFoundException.class,()-> quizService.getById(1L));
//        verify(quizRepository,times(1)).findById(1L);
//    }
//
//}
