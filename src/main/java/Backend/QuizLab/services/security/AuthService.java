package Backend.QuizLab.services.security;

import Backend.QuizLab.dtos.user.LoginRequest;
import Backend.QuizLab.dtos.user.TokenPair;
import Backend.QuizLab.dtos.user.UserRegistrationRequest;
import Backend.QuizLab.mapper.user.UserMapper;
import Backend.QuizLab.models.user.User;
import Backend.QuizLab.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private TokenBlacklistService tokenBlacklistService;
    @Autowired
    private UserMapper mapper;

    public User register (UserRegistrationRequest userRequest){
        if (userRepository.existsByEmail(userRequest.email())){
            throw new IllegalArgumentException("Email Already taken");
        }
        if (userRepository.existsByUsername(userRequest.username())){
            throw new IllegalArgumentException("Username Already taken");
        }
        var user = mapper.toEntity(userRequest);
        return userRepository.save(user);
    }

    public TokenPair login (LoginRequest userRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userRequest.identifier(), userRequest.password())
        );
        return jwtService.generateTokenPair(authentication);
    }

    public void logout (String token){
        Date expirationTime = jwtService.extractExpiration(token);
        if (expirationTime != null){
            tokenBlacklistService.blacklistToken(token, expirationTime.toInstant());
        }
    }
}
