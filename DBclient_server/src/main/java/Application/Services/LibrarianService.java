package Application.Services;

import Application.Entities.Librarian;

import java.util.List;

public interface LibrarianService {
    Librarian save(Librarian librarian);
    void update(Librarian librarian);
    void delete(long id);
    List<Librarian> findAll();
    Librarian findById(long id);
    List<Librarian> findByIdLibrary(long libID);
    List<Librarian> findByHallNum(long hallNum);
    List<Librarian> findByIdLibraryAndHallNum(long libID, long hallNum);
}
