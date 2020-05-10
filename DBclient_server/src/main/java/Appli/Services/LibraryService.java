package Appli.Services;

import Appli.Entities.Library;

import java.util.List;

public interface LibraryService {
    void save(Library library);
    void delete(Long id);
    Library getById(Long id);
    Library editLibrary(Library library);
    List<Library> findAll();
    List<Library> findById(long id);
    List<Library> findByHallNum(long hallNum);
    List<Library> findByIdAndHallNum(long id, long hallNum);
}
