package Backend.QuizLab.controllers;


import Backend.QuizLab.dtos.user.LoginRequest;
import Backend.QuizLab.dtos.user.TokenPair;
import Backend.QuizLab.dtos.user.UserRegistrationRequest;
import Backend.QuizLab.dtos.user.UserResponse;
import Backend.QuizLab.mapper.user.UserMapper;
import Backend.QuizLab.models.user.User;
import Backend.QuizLab.services.security.AuthService;
import Backend.QuizLab.services.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;


    @GetMapping("/health")
    public String health() {
        return "OK";
    }

    @GetMapping("/secure")
    public String secure() {
        return "Secure";
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRegistrationRequest req) {
        User created = authService.register(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.toResponse(created));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenPair> login(@RequestBody @Valid LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> me(Principal principal) {
        String identifier = principal.getName();
        User user = userService.findByUsernameOrEmail(identifier);
        return ResponseEntity.ok(userMapper.toResponse(user));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String authorizationHeader) {
        if (authorizationHeader!= null && authorizationHeader.startsWith("Bearer ")){
            String token = authorizationHeader.substring(7);
            authService.logout(token);
            return new ResponseEntity<>("User logged out", HttpStatus.OK);
        }

        return new ResponseEntity<>("No token provided", HttpStatus.BAD_REQUEST);
    }
}
