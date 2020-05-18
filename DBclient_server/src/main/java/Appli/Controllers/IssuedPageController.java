package Appli.Controllers;

import Appli.Entities.IssuedBook;
import Appli.Services.Impl.IssuedBookServiceImpl;
import Appli.Services.IssuedBookService;
import Appli.UserInterface.Pages.IssuedPage.IssuedForm;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IssuedPageController {


    private IssuedForm issuedForm;
    private IssuedBookService issuedBookService;

    private Long id_record;
    private Long id_reader;
    private Long id_edition;
    private Long id_librarian;
    private Boolean isReturned;
    private Date extraditionDate;
    private Date returnedDate;
    private boolean lessExtradition;
    private boolean moreReturned;


    public IssuedPageController(IssuedForm issuedForm) {
        this.issuedForm = issuedForm;
        this.issuedBookService = new IssuedBookServiceImpl();

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
                if(isReturned == null){
                    issuedBookList = issuedBookService.findAll();
                }else if(isReturned){
                    issuedBookList = issuedBookService.findReturned();
                }else{
                    issuedBookList = issuedBookService.findNotReturned();
                }
            } else if (id_edition != 0 && id_librarian != 0) {
                issuedBookList = issuedBookService.findByIdEditionAndIdLibrarian(id_edition, id_librarian);
            } else if (id_edition != 0){
                issuedBookList = issuedBookService.findByIdEdition(id_edition);
            } else if (id_reader != 0){
                if(isReturned == null){
                    issuedBookList = issuedBookService.findByIdReader(id_reader);
                }else if(isReturned){
                    issuedBookList = issuedBookService.findByIdReaderAndReturned(id_reader);
                }else
                    issuedBookList = issuedBookService.findByIdReaderAndNotReturned(id_reader);
            } else if (id_librarian != 0) {
                if(isReturned == null){
                    issuedBookList = issuedBookService.findByIdLibrarian(id_librarian);
                }else if(isReturned){
                    issuedBookList = issuedBookService.findByIdLibrarianAndReturned(id_librarian);
                }else
                    issuedBookList = issuedBookService.findByIdLibrarianAndNotReturned(id_librarian);

            }else if(extraditionDate != null && returnedDate != null){
                issuedBookList = issuedBookService.findBetweenDates(extraditionDate,returnedDate);
            } else if (extraditionDate != null && returnedDate == null){
                if(lessExtradition){
                    issuedBookList = issuedBookService.findByLessDateExtradition(extraditionDate);
                }else
                    issuedBookList = issuedBookService.findByMoreDateExtradition(extraditionDate);
            } else if (extraditionDate == null && returnedDate != null){
                if(moreReturned){
                    issuedBookList = issuedBookService.findByMoreDateReturn(returnedDate);
                }else
                    issuedBookList = issuedBookService.findByLessDateReturn(returnedDate);
            }
        });

    }
}
