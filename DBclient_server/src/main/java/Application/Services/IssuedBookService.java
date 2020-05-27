package Application.Services;

import Application.Entities.AllReader;
import Application.Entities.Information;
import Application.Entities.IssuedBook;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IssuedBookService {
    void save(IssuedBook issuedBook);
    void delete(Long id_record);
    IssuedBook update(IssuedBook issuedBook);
    IssuedBook findById(Long id);
    List<IssuedBook> findAll();

    List<IssuedBook> findByIdReader(Long id_reader);
    List<IssuedBook> findByIdEdition(Long id_edition);

    List<IssuedBook> findByIdReaderAndIdEdition(Long id_reader, Long id_edition);

    List<IssuedBook> findReturned();
    List<IssuedBook> findNotReturned();

    List<IssuedBook> findByIdReaderAndReturned(Long id_reader);
    List<IssuedBook> findByIdReaderAndNotReturned(Long id_reader);

    List<IssuedBook> findByIdLibrarian(Long id_librarian);
    List<IssuedBook> findByIdLibrarianAndReturned(Long id_librarian);
    List<IssuedBook> findByIdLibrarianAndNotReturned(Long id_librarian);

    List<IssuedBook> findByIdEditionAndIdLibrarian(Long id_edition, Long id_librarian);

    List<IssuedBook> findByMoreDateExtradition(Date dateExtradition);
    List<IssuedBook> findByLessDateExtradition(Date dateExtradition);
    List<IssuedBook> findByMoreDateReturn(Date dateReturn);
    List<IssuedBook> findByLessDateReturn(Date dateReturn);

    List<IssuedBook> findBetweenDates(Date dateExtradition, Date dateReturn);

    boolean isReturned(Long id_edition);
    List<AllReader> findReadersWithTitle(String title);
    List<AllReader> findReadersWithType(String type);

    boolean isRegistered(Long id_reader, Long id_edition);

    List<IssuedBook> findReadyBooks();

    List<AllReader> findReadersByIdLibraryAndPeriod(long idLibrarian, Date startDate, Date endDate);

    List<AllReader> findReadersNotAttendingLibrary(Date startDate, Date endDate);
    Map<AllReader, String> findReadersWithTitle(String composition, Date startDate, Date endDate);
}
