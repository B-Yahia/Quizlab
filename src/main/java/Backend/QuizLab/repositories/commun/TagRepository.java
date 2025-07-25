package Backend.QuizLab.repositories.commun;

import Backend.QuizLab.models.commun.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag,Long> {
}
