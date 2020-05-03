package Repository;

import Entities.Librarian;
import Entities.Library;
import org.springframework.data.repository.CrudRepository;

public interface LibraryRepository extends CrudRepository<Library, Long> {
    Library findById_library(Long id_library);
}
