package Appli.Services;

import Appli.Entities.Library;

import java.util.List;

public interface LibraryService {
    Library addLibrary(Library library);
    void delete(Long id);
    Library getById(Long id);
    Library editLibrary(Library library);
    List<Library> getAll();
}
