package Backend.QuizLab.services.user;

import Backend.QuizLab.models.user.User;
import Backend.QuizLab.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser (User user){
        return userRepository.save(user);
    }

    public User getUserById (long id){
        return userRepository.findById(id).orElseThrow(()-> new RuntimeException(" User with Id "+id+" not found "));
    }

    public User updateUserPassword (long id, String password){
        User user = getUserById(id);
        user.setPassword(password);
        return createUser(user);
    }
}
