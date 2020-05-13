package Appli.Controllers;

import Appli.Entities.Characteristic;
import Appli.Entities.Edition;
import Appli.Services.CharacteristicService;
import Appli.Services.EditionService;
import Appli.Services.Impl.CharacteristicServiceImpl;
import Appli.Services.Impl.EditionServiceImpl;
import Appli.UserInterface.Frames.Edition.libraryInformationEditionForm;
import Appli.UserInterface.Pages.EditionPage.EditionForm;

import javax.swing.*;

public class EditionsPageController {
    private EditionForm editionForm;
    private CharacteristicService charService;
    private EditionService editionService;
    
    private long cur_IdEdition;
    private String cur_type;
    private String cur_author;
    private String cur_title;
    private Characteristic cur_char;
    private libraryInformationEditionForm libraryInformation;

    public EditionsPageController(EditionForm editionForm){
        this.editionForm = editionForm;
        this.charService = new CharacteristicServiceImpl();
        this.editionService = new EditionServiceImpl();

        initializationListeners();
        setStartValues();
    }

    private void setStartValues(){
        cur_IdEdition = 0;
        cur_type = "";
        cur_author = "";
        cur_title = "";
    }

    private void initializationListeners() {
        editionForm.IdEditionTextField.addActionListener(e -> {
            try {
                cur_IdEdition = Long.parseLong(editionForm.IdEditionTextField.getText());
            }catch (NumberFormatException exception){
                JOptionPane.showMessageDialog(editionForm, "Введите корректный номер библиотеки");
                setStartValues();
            }
        });

        editionForm.typeTextField.addActionListener(e -> {
            String str = editionForm.typeTextField.getText();
            if(Checker.getInstance().checkString(str)){
                cur_type = str;
            }else{
                JOptionPane.showMessageDialog(editionForm, "Введите корректный тип издания");
                setStartValues();
            }
        });

        editionForm.authorTextField.addActionListener(e -> {
            String str = editionForm.authorTextField.getText();
            if(Checker.getInstance().checkString(str)){
                cur_author = str;
            }else{
                JOptionPane.showMessageDialog(editionForm, "Введите корректного автора");
                setStartValues();
            }
        });

        editionForm.titleTextField.addActionListener(e -> {
            String str = editionForm.titleTextField.getText();
            if(Checker.getInstance().checkString(str)){
                cur_title = str;
            }else{
                JOptionPane.showMessageDialog(editionForm, "Введите корректное название издания");
                setStartValues();
            }
        });

        editionForm.addButton.addActionListener(e -> {
            if(!cur_type.equals("") && !cur_author.equals("") && !cur_title.equals("")) {
                Characteristic characteristic = new Characteristic();
                characteristic.setType_edition(cur_type);
                characteristic.setAuthor(cur_author);
                characteristic.setTitle(cur_title);
                cur_char = charService.save(characteristic);
                if(cur_char.getId_edition() != null && cur_char.getId_edition()!= 0){
                    libraryInformation = new libraryInformationEditionForm(this);
                }
            }
            else{
                JOptionPane.showMessageDialog(editionForm, "Вы ввели не все параметры");
            }
            setStartValues();
        });

        libraryInformation.backButton.addActionListener(e -> {
            charService.delete(cur_char.getId_edition());
            cur_char = new Characteristic();
            setStartValues();
            libraryInformation.dispose();
            libraryInformation = null;
        });

        libraryInformation.addButton.addActionListener(e -> {
            if(libraryInformation.IdLib != 0 && libraryInformation.hallNum != 0 && libraryInformation.rackNum != 0
                    && libraryInformation.shelfNum != 0 ) {
                Edition edition = new Edition();
                edition.setId_edition(cur_char.getId_edition());
                edition.setId_library(libraryInformation.IdLib);
                edition.setHall_num(libraryInformation.hallNum);
                edition.setRack_num(libraryInformation.rackNum);
                edition.setShelf_num(libraryInformation.shelfNum);
                editionService.save(edition);
            }
        });

    }

    public void queryForSaveEditionLibraryInfo(long id_library, int hall_num, int rack_num, int shelf_num, boolean cur_date){

    }
}
