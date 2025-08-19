package Backend.QuizLab.repositories;

import Backend.QuizLab.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

    // Combined lookup for login (username OR email)
    default Optional<User> findByIdentifier(String identifier) {
        // Simple branch; can optimize with a custom query later
        return findByUsername(identifier).or(() -> findByEmail(identifier));
    }

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
