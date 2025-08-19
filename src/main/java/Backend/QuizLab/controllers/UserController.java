package Backend.QuizLab.controllers;

import Backend.QuizLab.controllers.auth.UserRegistrationRequest;
import Backend.QuizLab.dtos.UserDTO;
import Backend.QuizLab.mapper.user.UserMapper;
import Backend.QuizLab.services.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper mapper;

    @PostMapping
    public ResponseEntity<UserDTO> create (@Valid @RequestBody UserRegistrationRequest request ){
        var entity = userService.register(request);
        return new ResponseEntity<>(mapper.toDTO(entity), HttpStatus.CREATED);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<UserDTO> get (@PathVariable long id){
//        return new ResponseEntity<>(mapper.toDTO(userService.getUserById(id)),HttpStatus.FOUND);
//    }
}
