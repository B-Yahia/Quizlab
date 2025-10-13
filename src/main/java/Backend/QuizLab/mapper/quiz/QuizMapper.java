package Backend.QuizLab.mapper.quiz;

import Backend.QuizLab.dtos.quiz.QuizDTO;
import Backend.QuizLab.models.quiz.Quiz;
import Backend.QuizLab.services.quiz.QuizAttemptService;
import Backend.QuizLab.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuizMapper {
    @Autowired
    private QuizQuestionMapper questionMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private QuizAttemptService attemptService;

    public Quiz toEntity(QuizDTO dto) {
        var entity = new Quiz();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setTimeLimit(dto.getTimeLimit());
        entity.setCreator(userService.getUserById(dto.getCreatorId()));
        entity.setQuestions(questionMapper.toEntities(dto.getQuestions()));
        entity.setAverageScore(dto.getAverageScore());
        entity.setHighestScore(dto.getHighestScore());
        entity.setIsPublished(dto.getIsPublished());
        entity.setRequireAccessCode(dto.getRequireAccessCode());
        entity.setAllowAnonymous(dto.getAllowAnonymous());
        entity.setAccessCode(dto.getAccessCode());
        return entity;
    }

    public Quiz toEntity(Backend.QuizLab.dtos.llm.quiz.Quiz aiQuiz){
        var entity = new Quiz();
        entity.setTitle(aiQuiz.title);
        entity.setDescription(aiQuiz.description);
        entity.setQuestions(questionMapper.AIResponseToEntities(aiQuiz.questions));
        return entity;
    }

    public QuizDTO toDTO(Quiz entity) {
        var dto = new QuizDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setTimeLimit(entity.getTimeLimit());
        dto.setCreatorId(entity.getCreator().getId());
        dto.setQuestions(questionMapper.toDTOs(entity.getQuestions()));
        dto.setAverageScore(entity.getAverageScore());
        dto.setHighestScore(entity.getHighestScore());
        dto.setIsPublished(entity.getIsPublished());
        dto.setRequireAccessCode(entity.getRequireAccessCode());
        dto.setAllowAnonymous(entity.getAllowAnonymous());
        dto.setAccessCode(entity.getAccessCode());
        dto.setAttemptsCount(attemptService.getCountOfAttemptOnQuiz(entity.getId()));
        return dto;
    }

    public List<QuizDTO> toDTOs(List<Quiz> entities) {
        List<QuizDTO> DTOs = new ArrayList<>();
        for (Quiz entity : entities) {
            DTOs.add(toDTO(entity));
        }
        return DTOs;
    }

    public List<Quiz> toEntities(List<QuizDTO> DTOs) {
        List<Quiz> entities = new ArrayList<>();
        for (QuizDTO dto : DTOs) {
            entities.add(toEntity(dto));
        }
        return entities;
    }
}
