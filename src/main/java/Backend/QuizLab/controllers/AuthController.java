package Backend.QuizLab.controllers;


import Backend.QuizLab.dtos.user.LoginRequest;
import Backend.QuizLab.dtos.user.UserRegistrationRequest;
import Backend.QuizLab.dtos.user.UserResponse;
import Backend.QuizLab.mapper.user.UserMapper;
import Backend.QuizLab.models.user.User;
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

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper mapper;


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
        System.out.println("register");
        User created = userService.register(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(created));
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody @Valid LoginRequest loginRequest,
                                              HttpServletRequest request,
                                              HttpServletResponse response) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.identifier(),
                        loginRequest.password()
                )
        );
        // Set context
        var context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);

//        // Force session creation & explicit save
//        HttpSession session = request.getSession(true);

        // Explicit save using same repository as config (defensive)
        new HttpSessionSecurityContextRepository()
                .saveContext(context, request, response);

        User user = userService.findByUsernameOrEmail(authentication.getName());
        return ResponseEntity.ok(mapper.toResponse(user));
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> me(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        User u = userService.findByUsernameOrEmail(authentication.getName());
        return ResponseEntity.ok(mapper.toResponse(u));
    }
    @GetMapping("/session-info")
    public Map<String,Object> sessionInfo(HttpServletRequest request, Authentication auth) {
        var map = new HashMap<String,Object>();
        var session = request.getSession(false);
        map.put("sessionExists", session != null);
        map.put("sessionId", session != null ? session.getId() : null);
        map.put("principal", auth == null ? null : auth.getName());
        return map;
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) session.invalidate();
        SecurityContextHolder.clearContext();
        return ResponseEntity.noContent().build();
    }
}
