package Backend.QuizLab.mapper.quiz;

import Backend.QuizLab.dtos.quiz.QuizAttemptResponse;
import Backend.QuizLab.models.quiz.QuizAttempt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuizAttemptResponseMapper {
    @Autowired
    private QuizMapper quizMapper;
    @Autowired
    private QuizAnswerRecordResponseMapper answerRecordResponseMapper;

    public QuizAttemptResponse toResponse (QuizAttempt entity){
        var response = new QuizAttemptResponse();
        response.setId(entity.getId());
        response.setQuiz(quizMapper.toDTO(entity.getQuiz()));
        response.setGraded(entity.isGraded());
        response.setDuration(entity.getDuration());
        response.setAnswerRecords(answerRecordResponseMapper.toResponses(entity.getAnswerRecords()));
        response.setPercentageScore(entity.getPercentageScore());
        response.setTotalScore(entity.getTotalScore());
        response.setProgressionStatus(entity.getProgressionStatus());
        return response;
    }

    public List<QuizAttemptResponse> toResponses (List<QuizAttempt> entities){
        List<QuizAttemptResponse> responses = new ArrayList<>();
        for (QuizAttempt entity : entities){
            responses.add(toResponse(entity));
        }
        return responses;
    }
}
