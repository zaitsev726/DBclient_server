package Repository;

import Entities.AllReader;
import Entities.Librarian;
import org.springframework.data.repository.CrudRepository;

public interface LibrarianRepository extends CrudRepository<Librarian, Long> {
    Librarian findById_librarian(Long id_librarian);
}
