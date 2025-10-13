package Backend.QuizLab.mapper.quiz;

import Backend.QuizLab.dtos.quiz.QuizAnswerRecordResponse;
import Backend.QuizLab.models.quiz.QuizAnswerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuizAnswerRecordResponseMapper {
    @Autowired
    private QuizQuestionMapper questionMapper;
    @Autowired
    private QuizOptionMapper optionMapper;

    public QuizAnswerRecordResponse toResponse (QuizAnswerRecord entity){
        var response = new QuizAnswerRecordResponse();
        response.setId(entity.getId());
        response.setTextResponse(entity.getTextResponse());
        response.setCorrect(entity.isCorrect());
        response.setQuestion(questionMapper.toDTO(entity.getQuestion()));
        response.setGraded(entity.isGraded());
        response.setPointsEarned(entity.getPointsEarned());
        response.setSelectedOptions(optionMapper.toDTOs(entity.getSelectedOptions()));
        return response;
    }

    public List<QuizAnswerRecordResponse> toResponses ( List<QuizAnswerRecord> entities){
        List<QuizAnswerRecordResponse> responses = new ArrayList<>();
        for (QuizAnswerRecord entity : entities){
            responses.add(toResponse(entity));
        }
        return responses;
    }
}
