package Repository;

import Entities.Edition;
import org.springframework.data.repository.CrudRepository;


public interface EditionRepository extends CrudRepository<Edition, Long> {
    Edition findById_edition(Long id_edition);
}
