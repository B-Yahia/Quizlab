package Backend.QuizLab.repositories;

import Backend.QuizLab.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    default Optional<User> findByIdentifier(String identifier) {
        return findByUsername(identifier).or(() -> findByEmail(identifier));
    }

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
