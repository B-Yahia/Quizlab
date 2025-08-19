//package Backend.QuizLab.services.quiz;
//
//import Backend.QuizLab.exceptions.ResourceNotFoundException;
//import Backend.QuizLab.models.quiz.QuizOption;
//import Backend.QuizLab.repositories.quiz.QuizOptionRepository;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Optional;
//
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class QuizOptionServiceTest {
//
//    @Mock
//    private QuizOptionRepository optionRepository;
//
//    @InjectMocks
//    private QuizOptionService optionService;
//
//    @Test
//    public void QuizOptionService_CreateEntity_ReturnEntity(){
//        // Arrange
//        var option1 = new QuizOption("public",true,null);
//        var option2 = new QuizOption("private",false,null);
//        when( optionRepository.save(option2)).thenReturn(option2);
//        when( optionRepository.save(option1)).thenReturn(option1);
//
//        // Act
//        var returnedOption1 = optionService.create(option1);
//        var returnedOption2 = optionService.create(option2);
//
//        // Assert
//        assert returnedOption1 != null;
//        assert returnedOption2 != null;
//        assert returnedOption1.getStatement().equals("public") ;
//        assert returnedOption2.getStatement().equals("private");
//        verify(optionRepository,times(2)).save(any(QuizOption.class));
//    }
//
//    @Test
//    public void QuizOptionService_GetEntityById_ReturnEntity(){
//        // Arrange
//        var option = new QuizOption();
//        when(optionRepository.findById(1L)).thenReturn(Optional.of(option));
//
//        // Act
//        var returnOption = optionService.getById(1L);
//
//        // Assert
//        assert returnOption != null;
//        verify(optionRepository,times(1)).findById(anyLong());
//    }
//
//    @Test
//    public void QuizOptionService_GetEntityById_NoneExistingEntity(){
//        // Arrange
//        when(optionRepository.findById(1L)).thenReturn(Optional.empty());
//
//        // Act & Assert
//        Assertions.assertThrows(ResourceNotFoundException.class,()-> optionService.getById(1L));
//        verify(optionRepository,times(1)).findById(anyLong());
//    }
//}
