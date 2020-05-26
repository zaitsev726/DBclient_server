package Application.Controllers;

import Application.Entities.AllReader;

import Application.Entities.Library;
import Application.Entities.Types.*;
import Application.Services.AllReaderService;
import Application.Services.Impl.AbstractReaderServiceImpl;
import Application.Services.Impl.AllReaderServiceImpl;
import Application.Services.Impl.LibraryServiceImpl;
import Application.Services.LibraryService;
import Application.UserInterface.Frames.ProfessionForm;
import Application.UserInterface.Frames.SearchReadersForm;
import Application.UserInterface.Pages.ReadersPage.ReadersForm;

import javax.persistence.NoResultException;
import javax.swing.*;
import java.util.ArrayList;
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
        form.nameTextField.setText("");
        cur_surname = "";
        form.surnameTextField.setText("");
        cur_patronymic = "";
        form.patronymicTextField.setText("");
      //  cur_type = "pensioner";
        cur_lib_id = 0;
        form.idTextField.setText("");
        cur_Library = null;
        form.typeComboBox.setSelectedItem("<none>");
    }
    /**
     * Функия иницилизирующая листенеры Readers Form
     */
    private void initializationListeners(){

        form.nameTextField.addActionListener(e -> {
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
        });

        form.surnameTextField.addActionListener(e -> {
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
        });

        form.patronymicTextField.addActionListener(e -> {
            String patronymic = form.patronymicTextField.getText();
            if(!patronymic.equals(cur_patronymic)){
                if(Checker.getInstance().checkString(patronymic)){
                    cur_patronymic = patronymic;

                }
                else{
                    JOptionPane.showMessageDialog(form, "Введите корректное отчество");
                }
            }
        });

        form.idTextField.addActionListener(e -> {
            try {
                long id_library = Long.parseLong(form.idTextField.getText());
                try{
                    cur_Library = libraryService.getById(id_library);
                }catch (NoResultException exp){
                    JOptionPane.showMessageDialog(form, "Такой библиотеки не существует");
                    form.idTextField.setText("");
                    id_library = 0;
                }
                cur_lib_id = id_library;
            }catch (NumberFormatException exception){
                JOptionPane.showMessageDialog(form, "Введите корректный номер библиотеки");
                cur_Library = null;
                cur_lib_id = 0;
            }
        });

        form.typeComboBox.addActionListener(e -> {
            JComboBox box = (JComboBox) e.getSource();
            String str = (String) box.getSelectedItem();
            System.out.println(str);
            cur_type = str;
        });

        form.addButton.addActionListener(e -> {
            if (cur_type.equals("<none>")) {
                JOptionPane.showMessageDialog(form, "Невозможно добавить читателя без профессии");
            } else {
                if (!(cur_name.equals("") ||
                        cur_surname.equals("") ||
                        cur_patronymic.equals("") ||
                        cur_lib_id == 0 ||
                        cur_type.equals(""))) {

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
                  //  setStartValues();
                }else{
                    JOptionPane.showMessageDialog(form, "Вы ввели не все данные");
                }
            }
        });

        form.removeButton.addActionListener(e -> {
            // Отображение введенного текста
            List<AllReader> readers = null;
            System.out.println("нажато удаление");
            readers = searchReaders();
            if (readers.size() > 0) {
                for(AllReader reader: readers) {
                    readerService.delete(reader);
                }
            }else{
                JOptionPane.showMessageDialog(form, "Таких читателей не существует");
            }
        });

        form.searchButton.addActionListener(e -> {
            List<AllReader> readers = null;
            System.out.println("нажат поиск");
            readers = searchReaders();
            ArrayList<String[]> resultList = new ArrayList<>();
            System.out.println(readers);
            if (readers.size() > 0) {
                for(AllReader reader: readers) {
                    resultList.add(new String[]{String.valueOf(reader.getId_reader()), reader.getType(), reader.getName(), reader.getSurname(), reader.getPatronymic(), String.valueOf(reader.getId_library())});
                }
                searchReadersForm.updateTable(resultList);
                searchReadersForm.setVisible(true);
            }else{
                JOptionPane.showMessageDialog(form, "Таких читателей не существует");
            }


            setStartValues();
        });
    }

    private List<AllReader> searchReaders(){
        List<AllReader> readers = null;
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
        return readers;
    }


    /**
     * Функция, получающая на вход параметры, введеные из формы профессиий.
     * @param param Массив параметров, для каждой профессии свой.
     * @param readerType тип читателя, который необходимо сохранить
     * @param typeOfSetting тип функции, сохранение или обновление читатетял
     */
    public synchronized void setParam(ArrayList<String> param, String readerType, String typeOfSetting){
        System.out.println(param);
        System.out.println(readerType);
        System.out.println("***** SAVING *****");

        AbstractReader type = null;
        AllReader reader = saved;


        if(typeOfSetting.equals("update")){
            reader = update;
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

    /**
     * Выполняет роль посредника между сохранением в базу данных и интерфейсом поиска
     * На вход получает данные, которые изменились в таблице поиска
     * @param id_reader данные читателя, которые необходимо поменть
     * @param changed_param параметр, который изменился в интерфейсе поиска.
     */
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
            //убрано для редактирования типа читатетля
           // if(!readerService.findById(id_reader).getType().equals(type)) {
                updateProffesionForm.changePanel(type);
                updateProffesionForm.setVisible(true);
           // }
        }else{
            readerService.update(update);
        }
    }
    public void queryForDelete(long id_reader, String type, String name, String surname, String patronymic, long id_library){
        AllReader reader = new AllReader();
        reader.setName(name);
        reader.setSurname(surname);
        reader.setPatronymic(patronymic);
        reader.setId_library(id_library);
        reader.setType(type);
        reader.setId_reader(id_reader);
        readerService.delete(reader);
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
