package Backend.QuizLab.repositories.commun;

import Backend.QuizLab.models.commun.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {
}
