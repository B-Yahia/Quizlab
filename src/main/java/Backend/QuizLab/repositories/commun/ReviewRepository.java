package Backend.QuizLab.repositories.commun;

import Backend.QuizLab.models.commun.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Long> {
}
