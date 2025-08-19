package Backend.QuizLab.mapper.quiz;

import Backend.QuizLab.dtos.quiz.QuizAttemptDTO;
import Backend.QuizLab.models.quiz.QuizAttempt;
import Backend.QuizLab.services.quiz.QuizService;
import Backend.QuizLab.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuizAttemptMapper {

    @Autowired
    private QuizAnswerRecordMapper answerRecordMapper ;
    @Autowired
    private QuizService quizService ;
    @Autowired
    private UserService userService ;

    public QuizAttempt toEntity (QuizAttemptDTO dto ){
        var entity = new QuizAttempt();
        entity.setId(dto.getId());
        entity.setAnswerRecords(answerRecordMapper.toEntities(dto.getAnswerRecords()));
        entity.setQuiz(quizService.getById(dto.getQuizId()));
        entity.setGraded(dto.isGraded());
        entity.setDuration(dto.getDuration());
        entity.setTotalScore(dto.getTotalScore());
//        entity.setUser(dto.getUserId() != null ? userService.getUserById(dto.getUserId()) : null);
        entity.setProgressionStatus(dto.getProgressionStatus());
        entity.setPercentageScore(dto.getPercentageScore());
        return entity;
    }

    public QuizAttemptDTO toDTO (QuizAttempt entity){
        var dto = new QuizAttemptDTO();
        dto.setId(entity.getId());
        dto.setQuizId(entity.getQuiz().getId());
        dto.setAnswerRecords(answerRecordMapper.toDTOs(entity.getAnswerRecords()));
//        dto.setUserId(entity.getUser() == null ? null : entity.getUser().getId());
        dto.setGraded(entity.isGraded());
        dto.setDuration(entity.getDuration());
        dto.setPercentageScore(entity.getPercentageScore());
        dto.setTotalScore(entity.getTotalScore());
        dto.setProgressionStatus(entity.getProgressionStatus());
        return dto;
    }

}
