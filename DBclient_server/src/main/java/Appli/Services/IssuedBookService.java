package Appli.Services;

import Appli.Entities.IssuedBook;

import java.util.List;

public interface IssuedBookService {
    IssuedBook addIssuedBook(IssuedBook issuedBook);
    void delete(Long id);
    IssuedBook getById(Long id);
    IssuedBook editIssuedBook(IssuedBook issuedBook);
    List<IssuedBook> getAll();
}
