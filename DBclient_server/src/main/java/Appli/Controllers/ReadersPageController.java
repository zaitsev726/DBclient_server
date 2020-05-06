package Appli.Controllers;

import Appli.Entities.AllReader;

import Appli.Entities.Library;
import Appli.Services.AllReaderService;
import Appli.Services.Impl.AllReaderServiceImpl;
import Appli.Services.Impl.LibraryServiceImpl;
import Appli.Services.LibraryService;
import Appli.UserInterface.Frames.ProfessionForm;
import Appli.UserInterface.Pages.ReadersPage.ReadersForm;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.NoResultException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class ReadersPageController {
    private ReadersForm form;
    private ProfessionForm profissionForm;
    private String cur_name;
    private String cur_surname;
    private String cur_patronymic;
    private String cur_type;
    private long cur_lib_id;
    private Library cur_Library;
    private AllReaderService readerService;
    private LibraryService libraryService;

    public ReadersPageController(ReadersForm form){
        this.form = form;
        this.profissionForm = new ProfessionForm();

        readerService = new AllReaderServiceImpl();
        libraryService = new LibraryServiceImpl();
        setStartValues();
        initializationListeners();

    }
    private void setStartValues(){
        cur_name = "";
        cur_surname = "";
        cur_patronymic = "";
        cur_type = "";
        cur_lib_id = 0;
        cur_Library = null;
    }
    private void initializationListeners(){

        form.nameTextField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Отображение введенного текста
                String name = form.nameTextField.getText();
                if(!name.equals(cur_name)){
                    if(Checker.getInstance().checkString(name)){
                        cur_name = name;
                    }
                    else{
                        JOptionPane.showMessageDialog(form, "Введите корректное имя");
                    }
                }
            }
        });

        form.surnameTextField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Отображение введенного текста
                String surname = form.surnameTextField.getText();
                if(!surname.equals(cur_surname)){
                    if(Checker.getInstance().checkString(surname)){
                        cur_surname = surname;
                    }
                    else{
                        JOptionPane.showMessageDialog(form, "Введите корректную фамилию");
                    }
                }
            }
        });

        form.patronymicTextField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String patronymic = form.patronymicTextField.getText();
                if(!patronymic.equals(cur_patronymic)){
                    if(Checker.getInstance().checkString(patronymic)){
                        cur_patronymic = patronymic;

                    }
                    else{
                        JOptionPane.showMessageDialog(form, "Введите корректное отчество");
                    }
                }
            }
        });

        form.idTextField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    long id_library = Long.parseLong(form.idTextField.getText());
                    try{
                        cur_Library = libraryService.getById(id_library);
                    }catch (NoResultException exp){
                        JOptionPane.showMessageDialog(form, "Такой библиотеки не существует");
                        id_library = 0;
                    }
                    cur_lib_id = id_library;
                }catch (NumberFormatException exception){
                    JOptionPane.showMessageDialog(form, "Введите корректный номер библиотеки");
                    cur_Library = null;
                    cur_lib_id = 0;
                }
            }
        });

        form.typeComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox box = (JComboBox) e.getSource();
                String str = (String) box.getSelectedItem();
                System.out.println(str);
                cur_type = str;
            }
        });

        form.addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              /*  if(!(cur_name.equals("") ||
                        cur_surname.equals("") ||
                        cur_patronymic.equals("") ||
                        cur_lib_id == 0 ||
                        cur_type.equals(""))){
*/
                    AllReader reader = new AllReader();
                    reader.setName(cur_name);
                    reader.setSurname(cur_surname);
                    reader.setPatronymic(cur_patronymic);
                    reader.setId_library(cur_lib_id);
                    reader.setType(cur_type);
                    reader.setLibrary(cur_Library);
                   // readerService.save(reader);
                    profissionForm.changePanel(cur_type);
                    profissionForm.setVisible(true);
                    System.out.println("saved");
                    setStartValues();
                //}
            }
        });

        form.searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<AllReader> readers = null;
                System.out.println("нажат поиск");
                if(!cur_name.equals("") && !cur_surname.equals("") && !cur_patronymic.equals("")){
                    readers = readerService.findByNameAndSurnameAndPatronymic(cur_name,cur_surname,cur_patronymic);
                    System.out.println(cur_name);
                    System.out.println(cur_surname);
                    System.out.println(cur_patronymic);
                }else if(!cur_name.equals("") && !cur_surname.equals("")){
                    readers = readerService.findByNameAndSurname(cur_name,cur_surname);
                }else if(!cur_name.equals("") && !cur_patronymic.equals("")){
                    readers = readerService.findByNameAndPatronymic(cur_name,cur_patronymic);
                }else if(!cur_surname.equals("") && !cur_patronymic.equals("")){
                    readers = readerService.findBySurnameAndPatronymic(cur_surname,cur_patronymic);
                }else if(!cur_name.equals("")){
                    readers = readerService.findByName(cur_name);
                }else if(!cur_surname.equals("")){
                    readers = readerService.findBySurname(cur_surname);
                }else if(!cur_patronymic.equals("")){
                    readers = readerService.findByPatronymic(cur_patronymic);
                }
                System.out.println(readers);
                if(cur_lib_id != 0){

                }

                if(!cur_type.equals("")){

                }

                setStartValues();
            }
        });
    }
}
