package Appli.Controllers;

import Appli.Entities.AllReader;
import Appli.Entities.Library;
import Appli.Services.Impl.LibraryServiceImpl;
import Appli.Services.LibraryService;
import Appli.UserInterface.Frames.Library.SearchLibrariesForm;
import Appli.UserInterface.Frames.Library.SearchReadersInLibraryForm;
import Appli.UserInterface.Pages.Library.LibraryForm;

import javax.persistence.NoResultException;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class LibraryPageController {

    private LibraryForm libraryForm;
    private LibraryService libraryService;
    private SearchLibrariesForm searchLibrariesForm;
    private long cur_libID;
    private long cur_hallNum;

    public LibraryPageController(LibraryForm form){
        libraryForm = form;
        libraryService = new LibraryServiceImpl();
        searchLibrariesForm = new SearchLibrariesForm(this);
        initializationListeners();
        setStartValues();
    }

    private void setStartValues(){
        cur_libID = 0;
        cur_hallNum = 0;
    }

    private void initializationListeners() {
        libraryForm.IDTextField.addActionListener(e -> {
            try {
                cur_libID = Long.parseLong(libraryForm.IDTextField.getText());
            }catch (NumberFormatException exception){
                JOptionPane.showMessageDialog(libraryForm, "Введите корректный номер библиотеки");
                setStartValues();
            }
        });

        libraryForm.hallNumTextField.addActionListener(e -> {
            try {
                cur_hallNum = Long.parseLong(libraryForm.hallNumTextField.getText());
            }catch (NumberFormatException exception){
                JOptionPane.showMessageDialog(libraryForm, "Введите корректное количество залов");
                setStartValues();
            }
        });

        libraryForm.addButton.addActionListener(e -> {
            Library library = null;
            if(cur_hallNum!= 0 && cur_libID!= 0) {
                try {
                    library = libraryService.getById(cur_libID);
                } catch (NoResultException ignored) {
                }
                if (library == null) {
                    library = new Library();
                    library.setId_library(cur_libID);
                    library.setHalls_num(cur_hallNum);
                    libraryService.save(library);
                } else {
                    JOptionPane.showMessageDialog(libraryForm, "Такая библиотека уже существует");
                }
            }
            else{
                JOptionPane.showMessageDialog(libraryForm, "Вы ввели не все параметры");
            }
            setStartValues();
        });

        libraryForm.searchButton.addActionListener(e -> {
            List<Library> libraries = new ArrayList<>();
            ArrayList<String[]> resultList = new ArrayList<>();
            try {
                if (cur_libID == 0 && cur_hallNum == 0) {
                    libraries = libraryService.findAll();
                } else if (cur_libID != 0 && cur_hallNum == 0) {
                    libraries.add(libraryService.getById(cur_libID));
                } else if (cur_libID == 0 && cur_hallNum != 0) {
                    libraries = libraryService.findByHallNum(cur_hallNum);
                } else {
                    libraries = libraryService.findByIdAndHallNum(cur_libID, cur_hallNum);
                }
                System.out.println(libraries);
                if (libraries.size() > 0) {
                    for (Library library : libraries) {
                        resultList.add(new String[]{String.valueOf(library.getId_library()), String.valueOf(library.getHalls_num())});
                    }
                    searchLibrariesForm.updateTable(resultList);
                    searchLibrariesForm.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(libraryForm, "Таких библиотек не существует");
                }
            }catch (NoResultException ignored){
                JOptionPane.showMessageDialog(libraryForm, "Библиотек с таким ID не существует");
            }
            setStartValues();
        });

        libraryForm.readerSearchButton.addActionListener(e -> {
           if(cur_libID != 0){
               try {
                   Library library = libraryService.getById(cur_libID);
                   System.out.println(library.getReaders());
                   SearchReadersInLibraryForm searchReadersInLibraryForm = new SearchReadersInLibraryForm((List<AllReader>) library.getReaders());
               }catch (NoResultException ignored){
                   JOptionPane.showMessageDialog(libraryForm, "Библиотек с таким ID не существует");
               }
           }else {
               JOptionPane.showMessageDialog(libraryForm, "Введите ID библиотеки");
           }
        });

    }

    public void queryForUpdate(long libID, long hallNum){
        Library library = new Library();
        library.setId_library(libID);
        library.setHalls_num(hallNum);
        libraryService.update(library);
    }
    public void queryForDelete(long libID){
        libraryService.delete(libID);
    }

}
