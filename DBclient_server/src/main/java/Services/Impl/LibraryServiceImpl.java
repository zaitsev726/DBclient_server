package Services.Impl;

import Entities.Library;
import Repository.LibraryRepository;
import Services.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class LibraryServiceImpl implements LibraryService {
    @Autowired
    LibraryRepository LibraryRepository;

    @Override
    public Library addLibrary(Library Library) {
        return LibraryRepository.save(Library);
    }

    @Override
    public void delete(Long id) {
        LibraryRepository.deleteById(id);
    }

    @Override
    public Library getById(Long id) {
        return LibraryRepository.findById_library(id);
    }

    @Override
    public Library editLibrary(Library Library) {
        LibraryRepository.deleteById(Library.getId_library());
        return LibraryRepository.save(Library);
    }

    @Override
    public List<Library> getAll() {
        return (List<Library>) LibraryRepository.findAll();
    }
}
