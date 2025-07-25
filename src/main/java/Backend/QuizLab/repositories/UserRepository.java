package Backend.QuizLab.repositories;

import Backend.QuizLab.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
