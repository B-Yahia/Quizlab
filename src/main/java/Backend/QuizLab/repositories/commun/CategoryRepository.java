package Backend.QuizLab.repositories.commun;

import Backend.QuizLab.models.commun.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
