package Backend.QuizLab.services.commun;

import Backend.QuizLab.exceptions.ResourceNotFoundException;
import Backend.QuizLab.models.commun.Review;
import Backend.QuizLab.repositories.commun.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository repository;

    public Review save( Review review){
        return repository.save(review);
    }

    public Review getById (long id){
        return repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Review with the id "+ id +" not found"));
    }
}
