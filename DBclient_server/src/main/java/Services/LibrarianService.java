package Services;

import Entities.IssuedBook;
import Entities.Librarian;

import java.util.List;

public interface LibrarianService {
    Librarian addLibrarian(Librarian librarian);
    void delete(Long id);
    Librarian getById(Long id);
    Librarian editLibrarian(Librarian librarian);
    List<Librarian> getAll();
}
