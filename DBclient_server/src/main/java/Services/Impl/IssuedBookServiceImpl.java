package Services.Impl;

import Entities.IssuedBook;
import Repository.IssuedBookRepository;
import Services.IssuedBookService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class IssuedBookServiceImpl implements IssuedBookService {
    @Autowired
    IssuedBookRepository issuedBookRepository;

    @Override
    public IssuedBook addIssuedBook(IssuedBook issuedBook) {
        return issuedBookRepository.save(issuedBook);
    }

    @Override
    public void delete(Long id) {
        issuedBookRepository.deleteById(id);
    }

    @Override
    public IssuedBook getById(Long id) {
        return issuedBookRepository.findById_record(id);
    }

    @Override
    public IssuedBook editIssuedBook(IssuedBook issuedBook) {
        issuedBookRepository.deleteById(issuedBook.getId_edition());
        return issuedBookRepository.save(issuedBook);
    }

    @Override
    public List<IssuedBook> getAll() {
        return (List<IssuedBook>) issuedBookRepository.findAll();
    }
}
