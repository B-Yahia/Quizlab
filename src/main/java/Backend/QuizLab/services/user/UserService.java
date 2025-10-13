package Backend.QuizLab.services.user;

import Backend.QuizLab.dtos.user.UserRegistrationRequest;
import Backend.QuizLab.exceptions.ResourceNotFoundException;
import Backend.QuizLab.mapper.user.UserMapper;
import Backend.QuizLab.models.user.User;
import Backend.QuizLab.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService{
    @Autowired
    private UserRepository repository;
    @Autowired
    private UserMapper mapper;

    public User register (UserRegistrationRequest userRequest){
        if (repository.existsByEmail(userRequest.email())){
            throw new IllegalArgumentException("Email Already taken");
        }
        if (repository.existsByUsername(userRequest.username())){
            throw new IllegalArgumentException("Username Already taken");
        }
        var user = mapper.toEntity(userRequest);
        return repository.save(user);
    }

    public User getUserById (UUID id){
        return repository.findById(id).orElseThrow(()-> new RuntimeException(" User with Id "+id+" not found "));
    }
    public User findByIdentifierOrThrow(String identifier) {
        return repository.findByIdentifier(identifier)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public User findByUsernameOrEmail(String identifier) {
        return repository.findByIdentifier(identifier)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

}
