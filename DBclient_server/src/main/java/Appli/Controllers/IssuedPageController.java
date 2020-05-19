package Appli.Controllers;

import Appli.Entities.AllReader;
import Appli.Entities.Edition;
import Appli.Entities.IssuedBook;
import Appli.Entities.Librarian;
import Appli.Entities.Types.Pensioner;
import Appli.Services.AllReaderService;
import Appli.Services.EditionService;
import Appli.Services.Impl.AllReaderServiceImpl;
import Appli.Services.Impl.EditionServiceImpl;
import Appli.Services.Impl.IssuedBookServiceImpl;
import Appli.Services.Impl.LibrarianServiceImpl;
import Appli.Services.IssuedBookService;
import Appli.Services.LibrarianService;
import Appli.UserInterface.Frames.IssuedBook.SearchIssuedBookForm;
import Appli.UserInterface.Pages.IssuedPage.EditionSearchForm;
import Appli.UserInterface.Pages.IssuedPage.IssuedForm;


import javax.persistence.NoResultException;
import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IssuedPageController {
    private IssuedForm issuedForm;
    private EditionSearchForm editionSearchForm;

    private IssuedBookService issuedBookService;
    private LibrarianService librarianService;
    private EditionService editionService;
    private AllReaderService readerService;


    private long id_record;
    private long id_reader;
    private long id_edition;
    private long id_librarian;
    private Boolean isReturned;
    private Date extraditionDate;
    private Date returnedDate;
    private boolean lessExtradition;
    private boolean moreReturned;

    private SearchIssuedBookForm issuedBookForm;

    public IssuedPageController(IssuedForm issuedForm, EditionSearchForm editionSearchForm) {
        this.issuedForm = issuedForm;
        this.editionSearchForm = editionSearchForm;

        this.issuedBookService = new IssuedBookServiceImpl();
        this.librarianService = new LibrarianServiceImpl();
        this.editionService = new EditionServiceImpl();
        this.readerService = new AllReaderServiceImpl();

        issuedBookForm = new SearchIssuedBookForm(this);

        setStartValues();
        initializationListeners();
    }

    private void setStartValues() {
        id_record = 0L;
        id_reader = 0L;
        id_edition = 0L;
        id_librarian = 0L;
        isReturned = null;
        extraditionDate = null;
        returnedDate = null;
        lessExtradition = false;
        moreReturned = false;

    }

    private void initializationListeners() {

        issuedForm.idRecordTextField.addActionListener(e -> {
            try {
                id_record = Long.parseLong(issuedForm.idRecordTextField.getText());
            } catch (NumberFormatException ignored) {
                JOptionPane.showMessageDialog(issuedForm, "Введите корректный ID записи");
                JOptionPane.showMessageDialog(issuedForm, "Ваши значения сброшены на нулевые");
                setStartValues();
            }
        });

        issuedForm.idReaderTextField.addActionListener(e -> {
            try {
                id_reader = Long.parseLong(issuedForm.idReaderTextField.getText());
            } catch (NumberFormatException ignored) {
                JOptionPane.showMessageDialog(issuedForm, "Введите корректный ID читателя");
                JOptionPane.showMessageDialog(issuedForm, "Ваши значения сброшены на нулевые");
                setStartValues();
            }
        });

        issuedForm.idEditionTextField.addActionListener(e -> {
            try {
                id_edition = Long.parseLong(issuedForm.idEditionTextField.getText());
            } catch (NumberFormatException ignored) {
                JOptionPane.showMessageDialog(issuedForm, "Введите корректный ID издания");
                JOptionPane.showMessageDialog(issuedForm, "Ваши значения сброшены на нулевые");
                setStartValues();
            }
        });

        issuedForm.idLibrarianTextField.addActionListener(e -> {
            try {
                id_librarian = Long.parseLong(issuedForm.idLibrarianTextField.getText());
            } catch (NumberFormatException ignored) {
                JOptionPane.showMessageDialog(issuedForm, "Введите корректный ID записи");
                JOptionPane.showMessageDialog(issuedForm, "Ваши значения сброшены на нулевые");
                setStartValues();
            }
        });

        issuedForm.returnedCheckBox.addActionListener(e -> {
            isReturned = issuedForm.returnedCheckBox.isSelected();
        });

        issuedForm.extraditionTextField.addActionListener(e -> {
            try {
                extraditionDate = new SimpleDateFormat("yyyy-MM-dd").parse(issuedForm.extraditionTextField.getText());
            } catch (ParseException ignored) {
                JOptionPane.showMessageDialog(issuedForm, "Неправильный формат даты.Ожидается yyyy-MM-dd");
                JOptionPane.showMessageDialog(issuedForm, "Ваши значения сброшены на пустые");
                setStartValues();
            }
            ;
        });

        issuedForm.returnedTextField.addActionListener(e -> {
            try {
                returnedDate = new SimpleDateFormat("yyyy-MM-dd").parse(issuedForm.returnedTextField.getText());
            } catch (ParseException ignored) {
                JOptionPane.showMessageDialog(issuedForm, "Неправильный формат даты.Ожидается yyyy-MM-dd");
                JOptionPane.showMessageDialog(issuedForm, "Ваши значения сброшены на пустые");
                setStartValues();
            }
            ;
        });

        issuedForm.lessCheckBox.addActionListener(e -> {
            lessExtradition = issuedForm.lessCheckBox.isSelected();
        });

        issuedForm.moreCheckBox.addActionListener(e -> {
            moreReturned = issuedForm.moreCheckBox.isSelected();
        });

        issuedForm.searchButton.addActionListener(e -> {
            List<IssuedBook> issuedBookList = new ArrayList<>();
            if (id_record == 0 && id_edition == 0 && id_reader == 0 && id_librarian == 0 && extraditionDate == null && returnedDate == null) {
                if (isReturned == null) {
                    issuedBookList = issuedBookService.findAll();
                } else if (isReturned) {
                    issuedBookList = issuedBookService.findReturned();
                } else {
                    issuedBookList = issuedBookService.findNotReturned();
                }
            } else if (id_edition != 0 && id_librarian != 0) {
                issuedBookList = issuedBookService.findByIdEditionAndIdLibrarian(id_edition, id_librarian);
            } else if (id_edition != 0) {
                issuedBookList = issuedBookService.findByIdEdition(id_edition);
            } else if (id_reader != 0) {
                if (isReturned == null) {
                    issuedBookList = issuedBookService.findByIdReader(id_reader);
                } else if (isReturned) {
                    issuedBookList = issuedBookService.findByIdReaderAndReturned(id_reader);
                } else
                    issuedBookList = issuedBookService.findByIdReaderAndNotReturned(id_reader);
            } else if (id_librarian != 0) {
                if (isReturned == null) {
                    issuedBookList = issuedBookService.findByIdLibrarian(id_librarian);
                } else if (isReturned) {
                    issuedBookList = issuedBookService.findByIdLibrarianAndReturned(id_librarian);
                } else
                    issuedBookList = issuedBookService.findByIdLibrarianAndNotReturned(id_librarian);

            } else if (extraditionDate != null && returnedDate != null) {
                issuedBookList = issuedBookService.findBetweenDates(extraditionDate, returnedDate);
            } else if (extraditionDate != null && returnedDate == null) {
                if (lessExtradition) {
                    issuedBookList = issuedBookService.findByLessDateExtradition(extraditionDate);
                } else
                    issuedBookList = issuedBookService.findByMoreDateExtradition(extraditionDate);
            } else if (extraditionDate == null && returnedDate != null) {
                if (moreReturned) {
                    issuedBookList = issuedBookService.findByMoreDateReturn(returnedDate);
                } else
                    issuedBookList = issuedBookService.findByLessDateReturn(returnedDate);
            }

            if (issuedBookList.size() > 0) {
                ArrayList<String[]> resultList = new ArrayList<>();
                for (IssuedBook book : issuedBookList) {
                    String[] str = new String[7];
                    str[0] = String.valueOf(book.getId_record());
                    str[1] = String.valueOf(book.getId_reader());
                    str[2] = String.valueOf(book.getId_edition());
                    str[5] = String.valueOf(book.isIs_returned());
                    str[6] = String.valueOf(book.getId_librarian());
                    str[3] = (book.getDate_extradition().getYear() + 1900) + "-" + book.getDate_extradition().getMonth() + "-" + book.getDate_extradition().getDate();
                    if (book.getDate_return() == null) {
                        str[4] = "<null>";
                    } else
                        str[4] = (book.getDate_return().getYear() + 1900) + "-" + book.getDate_return().getMonth() + "-" + book.getDate_return().getDate();
                    resultList.add(str);
                }
                issuedBookForm.updateTable(resultList);
                issuedBookForm.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(issuedForm, "Таких записей нет");
            }
            setStartValues();           //????
        });

        editionSearchForm.searchButton.addActionListener(e -> {
            String title = editionSearchForm.getTitle();
            String type = editionSearchForm.getType();

            if (!title.equals("") && !type.equals("")) {
                JOptionPane.showMessageDialog(editionSearchForm, "Просили ввести только ОДИН параметр. Поиск отменен");
                editionSearchForm.setStartValues();
            } else if (!title.equals("") && type.equals("")) {
               // issuedBookService
            } else if (title.equals("") && !type.equals("")) {

            }
        });

    }

    public void queryForUpdateIssuedBook(long id_record, long id_reader, long id_edition, Date extradition, Date returned, boolean isReturned, long id_librarian, int changedParam) {
        IssuedBook issuedBook = new IssuedBook();
        issuedBook.setId_record(id_record);
        issuedBook.setId_reader(id_reader);
        issuedBook.setId_edition(id_edition);
        issuedBook.setDate_extradition(extradition);
        issuedBook.setDate_return(returned);
        issuedBook.setIs_returned(isReturned);
        issuedBook.setId_librarian(id_librarian);

        List<IssuedBook> bookList = new ArrayList<>();
        Librarian librarian;
        Edition edition;
        AllReader reader;
        switch (changedParam) {
            case (1):
                try {
                    reader = readerService.findById(id_reader);
                } catch (NoResultException ignored) {
                    JOptionPane.showMessageDialog(issuedBookForm, "Такого читателя нет");
                    return;
                }
                issuedBookService.update(issuedBook);
                break;
            case (2):
                try {
                    librarian = librarianService.findById(id_librarian);
                } catch (NoResultException ignored) {
                    JOptionPane.showMessageDialog(issuedBookForm, "Такого работника нет");
                    return;
                }
                try {
                    edition = editionService.findById(id_edition);
                } catch (NoResultException ignored) {
                    JOptionPane.showMessageDialog(issuedBookForm, "Такого издания нет");
                    return;
                }

                if (edition.getId_library().equals(librarian.getId_library())) {
                    issuedBookService.update(issuedBook);
                } else {
                    JOptionPane.showMessageDialog(issuedBookForm, "Такого издания нет у данного работника");
                    return;
                }

                break;
            case (3):
                if (returned != null)
                    if (extradition.getTime() >= returned.getTime()) {
                        JOptionPane.showMessageDialog(issuedBookForm, "Дата выдачи не может быть позже даты возврата");
                        return;
                    }
                try {
                    bookList = issuedBookService.findByIdEdition(id_edition);
                } catch (NoResultException ignored) {
                    bookList = new ArrayList<>();
                }
                for (IssuedBook book : bookList) {
                    if (book.getDate_return() != null) {
                        if (book.getDate_return().getTime() >= extradition.getTime()) {
                            JOptionPane.showMessageDialog(issuedBookForm, "В это время книга была занята");
                            return;
                        }
                    }
                    if (book.getDate_extradition().getTime() <= extradition.getTime() && !book.isIs_returned()) {
                        JOptionPane.showMessageDialog(issuedBookForm, "В это время книга была не возвращена");
                        return;
                    }
                }
                issuedBookService.update(issuedBook);
                break;
            case (4):
                if (returned.getTime() < extradition.getTime()) {
                    JOptionPane.showMessageDialog(issuedBookForm, "Дата возврата не может быть раньше даты выдачи");
                    return;
                }
                try {
                    bookList = issuedBookService.findByIdEdition(id_edition);
                } catch (NoResultException ignored) {
                    bookList = new ArrayList<>();
                }

                for (IssuedBook book : bookList) {
                    if (!book.isIs_returned() && book.getDate_extradition().getTime() < returned.getTime()) {
                        JOptionPane.showMessageDialog(issuedBookForm, "В это время книга еще не возвращена");
                        return;
                    }
                    if (book.isIs_returned() && book.getDate_return().getTime() > returned.getTime()) {
                        JOptionPane.showMessageDialog(issuedBookForm, "В это время книга была занята");
                        return;
                    }
                }
                issuedBookService.update(issuedBook);
                break;
            case (6):
                try {
                    librarian = librarianService.findById(id_librarian);
                } catch (NoResultException ignored) {
                    JOptionPane.showMessageDialog(issuedBookForm, "Такого работника нет");
                    return;
                }
                try {
                    edition = editionService.findById(id_edition);
                } catch (NoResultException ignored) {
                    JOptionPane.showMessageDialog(issuedBookForm, "Такого издания нет");
                    return;
                }
                if (edition.getId_library().equals(librarian.getId_library())) {
                    issuedBookService.update(issuedBook);
                } else {
                    JOptionPane.showMessageDialog(issuedBookForm, "У работника нет такого издания в библиотеке");
                    return;
                }
                break;
        }


    }
}
