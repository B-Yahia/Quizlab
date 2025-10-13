package Backend.QuizLab.mapper.user;

import Backend.QuizLab.dtos.user.UserRegistrationRequest;
import Backend.QuizLab.dtos.user.UserResponse;
import Backend.QuizLab.dtos.user.UserDTO;
import Backend.QuizLab.models.user.Role;
import Backend.QuizLab.models.user.User;
import Backend.QuizLab.services.quiz.QuizService;
import Backend.QuizLab.services.survey.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private QuizService quizService;
    @Autowired
    private SurveyService surveyService;

    public User toEntity (UserDTO dto) {
        var entity = new User();
        entity.setId(dto.getId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setUsername(dto.getUsername());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setRole(dto.getRole());
        entity.setEmailVerified(dto.isEmailVerified());
        entity.setActive(dto.isActive());
        return entity;
    }

    public UserDTO toDTO (User entity) {
        var dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setUsername(entity.getUsername());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        dto.setRole(entity.getRole());
        dto.setEmailVerified(entity.isEmailVerified());
        dto.setActive(entity.isActive());
        return dto;
    }

    public List<User> toEntities (List<UserDTO> DTOs){
        List<User> entities = new ArrayList<>();
        for (UserDTO dto : DTOs){
            entities.add(toEntity(dto));
        }
        return entities;
    }

    public List<UserDTO> toDTOs (List<User> entities){
        List<UserDTO> DTOs = new ArrayList<>();
        for (User entity : entities){
            DTOs.add(toDTO(entity));
        }
        return DTOs;
    }

    public UserResponse toResponse(User u) {
        return new UserResponse(
                u.getId(),
                u.getFirstName(),
                u.getLastName(),
                u.getUsername(),
                u.getEmail(),
                u.getRole(),
                u.isEmailVerified(),
                u.isActive(),
                quizService.getCountOfUserQuizzes(u.getId()),
                surveyService.getCountsOfUserSurveys(u.getId())
        );
    }
    public User toEntity (UserRegistrationRequest userRequest){
        var user = new User();
        user.setPassword(encoder.encode(userRequest.password()));
        user.setEmail(userRequest.email());
        user.setRole(Role.REGULAR_USER);
        user.setFirstName(userRequest.firstName());
        user.setLastName(userRequest.lastName());
        user.setUsername(userRequest.username());
        user.setEmailVerified(false);
        user.setActive(true);
        user.setDeleted(false);
        return user;
    }


}
