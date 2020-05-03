package Repository;

import Entities.Information;
import Entities.IssuedBook;
import org.springframework.data.repository.CrudRepository;

public interface IssuedBookRepository extends CrudRepository<IssuedBook, Long> {
    IssuedBook findById_record(Long id_record);
}
