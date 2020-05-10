package Appli.Controllers;

import Appli.Entities.AllReader;

import Appli.Entities.Library;
import Appli.Entities.Types.*;
import Appli.Services.AllReaderService;
import Appli.Services.Impl.AbstractReaderServiceImpl;
import Appli.Services.Impl.AllReaderServiceImpl;
import Appli.Services.Impl.LibraryServiceImpl;
import Appli.Services.LibraryService;
import Appli.UserInterface.Frames.ProfessionForm;
import Appli.UserInterface.Frames.SearchReadersForm;
import Appli.UserInterface.Pages.ReadersPage.ReadersForm;

import javax.persistence.NoResultException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ReadersPageController {
    private ReadersForm form;
    private ProfessionForm profissionForm;
    private SearchReadersForm searchReadersForm;
    private ProfessionForm updateProffesionForm;
    private AbstractReaderServiceImpl abstractReaderService;
    private String cur_name;
    private String cur_surname;
    private String cur_patronymic;
    private String cur_type;
    private long cur_lib_id;
    private Library cur_Library;
    private AllReaderService readerService;
    private LibraryService libraryService;

    private AllReader saved;
    private AllReader update;

    public ReadersPageController(ReadersForm form){
        //cur_type = "pensioner";
        cur_type = "<none>";
        this.form = form;
        this.profissionForm = new ProfessionForm(this, "save");
        this.searchReadersForm = new SearchReadersForm(this);
        this.updateProffesionForm = new ProfessionForm(this, "update");
        this.abstractReaderService = new AbstractReaderServiceImpl();

        readerService = new AllReaderServiceImpl();
        libraryService = new LibraryServiceImpl();
        setStartValues();
        initializationListeners();

    }
    private void setStartValues(){
        cur_name = "";
        cur_surname = "";
        cur_patronymic = "";
      //  cur_type = "pensioner";
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
                if (cur_type.equals("<none>")) {
                    JOptionPane.showMessageDialog(form, "Невозможно добавить читателя без профессии");
                } else {
              /*  if(!(cur_name.equals("") ||
                        cur_surname.equals("") ||
                        cur_patronymic.equals("") ||
                        cur_lib_id == 0 ||
                        cur_type.equals(""))){
*/
                    saved = new AllReader();
                    saved.setName(cur_name);
                    saved.setSurname(cur_surname);
                    saved.setPatronymic(cur_patronymic);
                    saved.setId_library(cur_lib_id);
                    saved.setType(cur_type);
                    saved.setLibrary(cur_Library);

                    profissionForm.changePanel(cur_type);
                    profissionForm.setVisible(true);
                    System.out.println("saved");

                }
            }
        });

        form.searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<AllReader> readers = null;
                System.out.println("нажат поиск");
                if(cur_name.equals("") && cur_surname.equals("") && cur_patronymic.equals(""))
                    readers = readerService.findAll();
                else if(!cur_name.equals("") && !cur_surname.equals("") && !cur_patronymic.equals("")){
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
                    if (readers != null) {
                        readers.removeIf(reader -> reader.getId_library() != cur_lib_id);
                    }
                }
                System.out.println(readers);
                if(!cur_type.equals("<none>")){
                    if (readers != null) {
                        readers.removeIf(reader -> !reader.getType().equals(cur_type) );
                    }
                }
                ArrayList<String[]> resultList = new ArrayList<>();
                System.out.println(readers);
                if (readers != null) {
                    for(AllReader reader: readers) {
                        resultList.add(new String[]{String.valueOf(reader.getId_reader()), reader.getType(), reader.getName(), reader.getSurname(), reader.getPatronymic(), String.valueOf(reader.getId_library())});
                    }
                }
                searchReadersForm.updateTable(resultList);
                searchReadersForm.setVisible(true);

                setStartValues();
            }
        });
    }

    public synchronized void setParam(ArrayList<String> param, String readerType, String typeOfSetting){
        System.out.println(param);
        System.out.println(readerType);
        System.out.println("***** SAVING *****");

        AbstractReader type = null;
        AllReader reader = saved;


        if(typeOfSetting.equals("update")){
            reader = update;
           // as.delete(reader.getReaderType());
            System.out.println("******************************************************" + reader.getReaderType());
            type = readerService.findById(update.getId_reader()).getReaderType();
        }
        System.out.println(reader);

        reader = readerService.save(reader);





        switch (readerType) {
            case ("pensioner"):
                Pensioner pensioner = new Pensioner();
                pensioner.setId_reader(reader.getId_reader());
                pensioner.setType("pensioner");
                pensioner.setId_pensioners(Long.valueOf(param.get(0)));
                abstractReaderService.save(pensioner);
                break;
            case ("schoolkid"):
                Schoolkid schoolkid = new Schoolkid();
                schoolkid.setId_reader(reader.getId_reader());
                schoolkid.setType("schoolkid");
                schoolkid.setId_school(Long.valueOf(param.get(0)));
                schoolkid.setGrade(Long.valueOf(param.get(1)));
                abstractReaderService.save(schoolkid);
                break;
            case ("scientist"):
                Scientist scientist = new Scientist();
                scientist.setId_reader(reader.getId_reader());
                scientist.setType("scientist");
                scientist.setAddress(param.get(0));
                scientist.setId_university(Long.valueOf(param.get(1)));
                abstractReaderService.save(scientist);
                break;
            case ("student"):
                Student student = new Student();
                student.setId_reader(reader.getId_reader());
                student.setType("student");
                student.setId_university(Long.valueOf(param.get(0)));
                student.setFaculty(param.get(1));
                student.setId_group(Long.valueOf(param.get(2)));
                abstractReaderService.save(student);
                break;
            case ("teacher"):
                Teacher teacher = new Teacher();
                teacher.setId_reader(reader.getId_reader());
                teacher.setType("teacher");
                teacher.setId_university(Long.valueOf(param.get(0)));
                teacher.setFaculty(param.get(1));
                abstractReaderService.save(teacher);
                break;
            case ("worker"):
                Worker worker = new Worker();
                worker.setId_reader(reader.getId_reader());
                worker.setType("worker");
                worker.setAddress(param.get(0));
                worker.setFirm(param.get(1));
                abstractReaderService.save(worker);
                break;
            default:
                JOptionPane.showMessageDialog(profissionForm, "Ошибка");
                break;

        }

        if(typeOfSetting.equals("update") && !(type.getType().equals(reader.getType()))){
            abstractReaderService.delete(type);
        }
        System.out.println("***** END OF SAVING *****");
        JOptionPane.showMessageDialog(profissionForm, reader.getName() + " " + reader.getSurname() + " сохранен");
    }

    public void queryForUpdate(long id_reader, String type, String name, String surname, String patronymic, long id_library, String changed_param){

        update = new AllReader();
        update.setName(name);
        update.setSurname(surname);
        update.setPatronymic(patronymic);
        update.setId_library(id_library);
        update.setType(type);
        update.setId_reader(id_reader);
        System.out.println(type);
        if(changed_param.equals("type") ) {
           // if(!readerService.findById(id_reader).getType().equals(type)) {
                updateProffesionForm.changePanel(type);
                updateProffesionForm.setVisible(true);
           // }
        }else{
            readerService.update(update);
        }
    }

    public void showCurrentLibrary(long id_library){
        Library library = libraryService.getById(id_library);
        JOptionPane.showMessageDialog(updateProffesionForm,
                new String[]{"Информация о библиотеке",
                            " ID библиотеки: " +library.getId_library(),
                            " Количество залов: " + library.getHalls_num(),
                            " Количество зарегистрированных читателей: " + library.getReaders().size()}, "Библиотека", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showCurrentProfession(long id_reader){
        AbstractReader reader = readerService.findById(id_reader).getReaderType();
        JOptionPane.showMessageDialog(updateProffesionForm,
                new String[]{"Информация о профессии",
                            reader.toString()}, "Профессия", JOptionPane.INFORMATION_MESSAGE);
    }
}
