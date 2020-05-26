package Application.Controllers;

import Application.Entities.AllReader;
import Application.Entities.Characteristic;
import Application.Entities.Information;
import Application.Entities.IssuedBook;
import Application.Services.AllReaderService;
import Application.Services.CharacteristicService;
import Application.Services.Impl.AllReaderServiceImpl;
import Application.Services.Impl.CharacteristicServiceImpl;
import Application.Services.Impl.InformationServiceImpl;
import Application.Services.Impl.IssuedBookServiceImpl;
import Application.Services.InformationService;
import Application.Services.IssuedBookService;
import Application.UserInterface.Frames.TakeBook.SearchMyBooksForm;
import Application.UserInterface.Frames.TakeBook.SearchNewBooksForm;
import Application.UserInterface.Pages.TakeBookPage.AuthorizationForm;
import Application.UserInterface.Pages.TakeBookPage.TakeBookForm;

import javax.persistence.NoResultException;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TakeBookPageController {

    private AuthorizationForm authorizationForm;
    private TakeBookForm takeBookForm;


    private AllReaderService allReaderService;
    private InformationService informationService;
    private IssuedBookService issuedBookService;
    private CharacteristicService characteristicService;
    private AllReader reader;


    private SearchMyBooksForm searchMyBooksForm;
    private SearchNewBooksForm searchNewBooksForm;

    public TakeBookPageController(AuthorizationForm authorizationForm, TakeBookForm takeBookForm) {
        this.authorizationForm = authorizationForm;
        this.takeBookForm = takeBookForm;

        this.allReaderService = new AllReaderServiceImpl();
        this.informationService = new InformationServiceImpl();
        this.issuedBookService = new IssuedBookServiceImpl();
        this.characteristicService = new CharacteristicServiceImpl();

        this.searchMyBooksForm = new SearchMyBooksForm(this);
        this.searchNewBooksForm = new SearchNewBooksForm(this);
        initializationListeners();
    }

    private void initializationListeners() {
        takeBookForm.myEditionsButton.addActionListener(e -> {
            searchMyEdition();
        });

        takeBookForm.takeNewButton.addActionListener(e -> {
            searchNewEdition();
        });

        takeBookForm.myInfoButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(takeBookForm,
                    new String[]{"Информация о читателе",
                            " Имя: " + reader.getName(),
                            " Фамилия: " + reader.getSurname(),
                            " Отчество: " + reader.getPatronymic(),
                            " Библиотека: " + reader.getId_library(),
                            " Профессия: " + reader.getType(),}, "Читатель", JOptionPane.INFORMATION_MESSAGE);
        });
    }

    public void setReader(long id_reader) throws NoResultException {
        this.reader = allReaderService.findById(id_reader);
    }

    public void showCurrentInformation(long id_edition) {
        Characteristic characteristic = characteristicService.findById(id_edition);
        JOptionPane.showMessageDialog(takeBookForm,
                new String[]{"Информация о издании",
                        " Автор: " + characteristic.getAuthor(),
                        " Название: " + characteristic.getTitle(),
                        " Тип: " + characteristic.getType_edition()}, "Издания", JOptionPane.INFORMATION_MESSAGE);
    }

    public void queryForReturn(long id_edition) {
        List<IssuedBook> issuedBooks = issuedBookService.findByIdReaderAndIdEdition(reader.getId_reader(), id_edition);
        IssuedBook book = null;
        for (IssuedBook books : issuedBooks) {
            if (!books.isIs_returned()) {
                book = books;
            }
        }
        if (book != null) {
            book.setIs_returned(true);
            book.setDate_return(new Date());

            issuedBookService.update(book);
            searchMyEdition();
            if(searchNewBooksForm.isVisible())
                searchNewEdition();
        }
    }

    private void searchMyEdition() {
        if (reader != null) {
            List<IssuedBook> issuedBooks = issuedBookService.findByIdReader(reader.getId_reader());
            if (issuedBooks.size() > 0) {
                ArrayList<String[]> resultList = new ArrayList<>();
                for (IssuedBook book : issuedBooks) {
                    String[] str = new String[7];
                    str[0] = String.valueOf(book.getId_record());
                    str[1] = String.valueOf(book.getId_edition());
                    str[4] = String.valueOf(book.isIs_returned());
                    str[5] = String.valueOf(book.getId_librarian());
                    str[2] = (book.getDate_extradition().getYear() + 1900) + "-" + book.getDate_extradition().getMonth() + "-" + book.getDate_extradition().getDate();
                    if (book.getDate_return() == null) {
                        str[3] = "<null>";
                    } else
                        str[3] = (book.getDate_return().getYear() + 1900) + "-" + book.getDate_return().getMonth() + "-" + book.getDate_return().getDate();
                    resultList.add(str);
                }
                searchMyBooksForm.updateTable(resultList);
                searchMyBooksForm.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(takeBookForm, "Таких записей нет");
            }
        } else
            JOptionPane.showMessageDialog(takeBookForm, "Ошибка");
    }

    public void queryForTakeBook(long id_edition, long id_librarian) {
        IssuedBook issuedBook = new IssuedBook();
        issuedBook.setId_edition(id_edition);
        issuedBook.setId_reader(reader.getId_reader());
        issuedBook.setIs_returned(false);
        issuedBook.setDate_extradition(new Date());
        issuedBook.setDate_return(null);
        issuedBook.setId_librarian(id_librarian);

        for(Information information : informationService.findByIdEdition(id_edition)){
            information.setPopularity(information.getPopularity() + 1);
            informationService.update(information);
        }
        issuedBookService.save(issuedBook);

        searchNewEdition();
        searchMyEdition();

    }

    private void searchNewEdition() {
        if (reader != null) {
            List<IssuedBook> returnedBooks = issuedBookService.findReadyBooks();
            List<Long> IdEditions = new ArrayList<>();
            List<IssuedBook> issuedBooks = issuedBookService.findNotReturned();

            for(IssuedBook book : issuedBooks){
                IdEditions.add(book.getId_edition());
            }

            System.out.println(returnedBooks);
            if (returnedBooks.size() > 0) {
                ArrayList<String[]> resultList = new ArrayList<>();
                for (IssuedBook book : returnedBooks) {
                    if(!IdEditions.contains(book.getId_edition())) {
                        resultList.add(new String[]{String.valueOf(book.getId_record()), String.valueOf(book.getId_edition()), String.valueOf(book.getId_librarian())});
                        IdEditions.add(book.getId_edition());
                    }
                }
                searchNewBooksForm.updateTable(resultList);
                searchNewBooksForm.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(takeBookForm, "Таких записей нет");
            }
        } else
            JOptionPane.showMessageDialog(takeBookForm, "Ошибка");
    }
}
