package Backend.QuizLab.services.user;

import Backend.QuizLab.exceptions.ResourceNotFoundException;
import Backend.QuizLab.models.user.User;
import Backend.QuizLab.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repository;


    @Override
    public UserDetails loadUserByUsername(String identifier) throws ResourceNotFoundException {
        User user = repository.findByIdentifier(identifier)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        // Future: incorporate isActive / isEmailVerified checks
        List<GrantedAuthority> auths = List.of(
                new SimpleGrantedAuthority("ROLE_" + user.getRole().name())
        );
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), // principal "name"
                user.getPassword(),
                user.isActive(),
                true,
                true,
                true,
                auths
        );
    }
}
