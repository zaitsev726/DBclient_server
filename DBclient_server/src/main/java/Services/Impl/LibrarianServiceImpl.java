package Services.Impl;

import Entities.Librarian;
import Repository.LibrarianRepository;
import Services.LibrarianService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class LibrarianServiceImpl implements LibrarianService {
    @Autowired
    LibrarianRepository LibrarianRepository;

    @Override
    public Librarian addLibrarian(Librarian Librarian) {
        return LibrarianRepository.save(Librarian);
    }

    @Override
    public void delete(Long id) {
        LibrarianRepository.deleteById(id);
    }

    @Override
    public Librarian getById(Long id) {
        return LibrarianRepository.findById_librarian(id);
    }

    @Override
    public Librarian editLibrarian(Librarian Librarian) {
        LibrarianRepository.deleteById(Librarian.getId_librarian());
        return LibrarianRepository.save(Librarian);
    }

    @Override
    public List<Librarian> getAll() {
        return (List<Librarian>) LibrarianRepository.findAll();
    }
}
