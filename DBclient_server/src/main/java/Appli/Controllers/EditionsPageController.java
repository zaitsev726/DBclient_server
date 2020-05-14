package Appli.Controllers;

import Appli.Entities.Characteristic;
import Appli.Entities.Edition;
import Appli.Entities.Information;
import Appli.Entities.Rule;
import Appli.Services.CharacteristicService;
import Appli.Services.EditionService;
import Appli.Services.Impl.CharacteristicServiceImpl;
import Appli.Services.Impl.EditionServiceImpl;
import Appli.Services.Impl.InformationServiceImpl;
import Appli.Services.InformationService;
import Appli.UserInterface.Frames.Edition.Information.SearchInformationForm;
import Appli.UserInterface.Frames.Edition.InvertaryInfo.SearchEditionForm;
import Appli.UserInterface.Frames.Edition.InvertaryInfo.inventoryInformationForm;
import Appli.UserInterface.Frames.Edition.Rules.SearchRulesForm;
import Appli.UserInterface.Pages.EditionPage.EditionForm;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EditionsPageController {
    private EditionForm editionForm;

    private CharacteristicService charService;
    private EditionService editionService;
    private InformationService informationService;

    private long cur_IdEdition;
    private String cur_type;
    private String cur_author;
    private String cur_title;
    private Characteristic cur_char;

    private inventoryInformationForm libraryInformation;
    private SearchInformationForm informationForm;
    private SearchEditionForm searchEditionForm;
    private SearchRulesForm searchRulesForm;

    public EditionsPageController(EditionForm editionForm) {
        this.editionForm = editionForm;
        this.informationForm = new SearchInformationForm(this);
        this.searchEditionForm = new SearchEditionForm(this);
        this.searchRulesForm = new SearchRulesForm(this);

        this.charService = new CharacteristicServiceImpl();
        this.editionService = new EditionServiceImpl();
        this.informationService = new InformationServiceImpl();

        setStartValues();
        initializationListeners();
    }

    private void setStartValues() {
        cur_IdEdition = 0;
        cur_type = "";
        cur_author = "";
        cur_title = "";
    }

    private void initializationListeners() {
       /* editionForm.IdEditionTextField.addActionListener(e -> {
            try {
                cur_IdEdition = Long.parseLong(editionForm.IdEditionTextField.getText());
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(editionForm, "Введите корректный номер библиотеки");
                setStartValues();
            }
        });*/

        editionForm.typeTextField.addActionListener(e -> {
            String str = editionForm.typeTextField.getText();
            if (Checker.getInstance().checkString(str)) {
                cur_type = str;
            } else {
                JOptionPane.showMessageDialog(editionForm, "Введите корректный тип издания");
                setStartValues();
            }
        });

        editionForm.authorTextField.addActionListener(e -> {
            String str = editionForm.authorTextField.getText();
            if (Checker.getInstance().checkString(str)) {
                cur_author = str;
            } else {
                JOptionPane.showMessageDialog(editionForm, "Введите корректного автора");
                setStartValues();
            }
        });

        editionForm.titleTextField.addActionListener(e -> {
            String str = editionForm.titleTextField.getText();
            if (Checker.getInstance().checkString(str)) {
                cur_title = str;
            } else {
                JOptionPane.showMessageDialog(editionForm, "Введите корректное название издания");
                setStartValues();
            }
        });

        editionForm.addButton.addActionListener(e -> {
            if (!cur_type.equals("") && !cur_author.equals("") && !cur_title.equals("")) {
                Characteristic characteristic = new Characteristic();
                characteristic.setType_edition(cur_type);
                characteristic.setAuthor(cur_author);
                characteristic.setTitle(cur_title);
                cur_char = charService.save(characteristic);
                if (cur_char.getId_edition() != null && cur_char.getId_edition() != 0) {
                    libraryInformation = new inventoryInformationForm(this);

                    libraryInformation.backButton.addActionListener(f -> {
                        charService.delete(cur_char.getId_edition());
                        cur_char = new Characteristic();
                        setStartValues();
                        libraryInformation.dispose();
                        libraryInformation = null;
                    });

                    libraryInformation.addButton.addActionListener(f -> {
                        if (libraryInformation.IdLib != 0 && libraryInformation.hallNum != 0 && libraryInformation.rackNum != 0
                                && libraryInformation.shelfNum != 0) {
                            Edition edition = new Edition();
                            edition.setId_edition(cur_char.getId_edition());
                            edition.setId_library(libraryInformation.IdLib);
                            edition.setHall_num(libraryInformation.hallNum);
                            edition.setRack_num(libraryInformation.rackNum);
                            edition.setShelf_num(libraryInformation.shelfNum);
                            edition.setDate_adding(new Date());
                            editionService.save(edition);

                            List<Information> informationList = new ArrayList<>();
                            ArrayList<String[]> resultList = new ArrayList<>();
                            for (int i = 0; i < 4; i++) {
                                Information inf = new Information();
                                inf.setId_edition(cur_char.getId_edition());

                                inf.setAuthor("Неизвестно");
                                inf.setComposition("Неизвестно");
                                inf.setPopularity(0);
                                informationList.add(inf);
                            }
                            informationService.insertStartInformation(informationList);
                            informationList = informationService.findByIdEdition(cur_char.getId_edition());
                            for (Information inf : informationList) {
                                resultList.add(new String[]{String.valueOf(inf.getId_record()), String.valueOf(inf.getId_edition()), inf.getAuthor(), inf.getComposition(), String.valueOf(inf.getPopularity())});
                            }
                            informationForm.updateTable(resultList);
                            informationForm.setVisible(true);

                            libraryInformation.dispose();
                            libraryInformation = null;

                        }
                    });

                    libraryInformation.searchButton.addActionListener(f -> {
                        JOptionPane.showMessageDialog(editionForm, "Вы добавляете Издание, а не ищите!");
                    });
                }
            } else {
                JOptionPane.showMessageDialog(editionForm, "Вы ввели не все параметры");
            }
            setStartValues();
        });

        editionForm.inventoryInformationButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(editionForm, "Информация на данной странице никак не учитывается!");
            libraryInformation = new inventoryInformationForm(this);

            libraryInformation.backButton.addActionListener(f -> {
                charService.delete(cur_char.getId_edition());
                cur_char = new Characteristic();
                setStartValues();
                libraryInformation.dispose();
                libraryInformation = null;
            });

            libraryInformation.searchButton.addActionListener(f -> {
                List<Edition> editionList = new ArrayList<>();
                long id_lib = libraryInformation.IdLib;
                int hall_num = libraryInformation.hallNum;
                int rack_num = libraryInformation.rackNum;
                int shelf_num = libraryInformation.shelfNum;

                if (id_lib != 0 && hall_num != 0 && rack_num != 0 && shelf_num != 0) {
                    editionList = editionService.findByAll(id_lib, hall_num, rack_num, shelf_num);
                } else if (id_lib != 0 && hall_num != 0 && rack_num != 0) {
                    editionList = editionService.findByIdLibAndHallNumAndRackNum(id_lib, hall_num, rack_num);
                } else if (id_lib != 0 && rack_num != 0 && shelf_num != 0) {
                    editionList = editionService.findByIdLibAndRackNumAndShelfNum(id_lib, rack_num, shelf_num);
                } else if (id_lib != 0 && hall_num != 0 && shelf_num != 0) {
                    editionList = editionService.findByIdLibAndHallNumAndShelfNum(id_lib, hall_num, shelf_num);
                } else if (hall_num != 0 && rack_num != 0 && shelf_num != 0) {
                    editionList = editionService.findByHallNumAndRackNumAndShelfNum(hall_num, rack_num, shelf_num);
                } else if (id_lib != 0 && hall_num != 0) {
                    editionList = editionService.findByIdLibAndHallNum(id_lib, hall_num);
                } else if (id_lib != 0 && rack_num != 0) {
                    editionList = editionService.findByIdLibAndRackNum(id_lib, rack_num);
                } else if (id_lib != 0 && shelf_num != 0) {
                    editionList = editionService.findByIdLibAndShelfNum(id_lib, shelf_num);
                } else if (hall_num != 0 && rack_num != 0) {
                    editionList = editionService.findByHallNumAndRackNum(hall_num,rack_num);
                } else if (hall_num != 0 && shelf_num != 0) {
                    editionList = editionService.findByHallNumAndShelfNum(hall_num,shelf_num);
                } else if (rack_num != 0 && shelf_num != 0) {
                    editionList = editionService.findByRackNumAndShelfNum(rack_num,shelf_num);
                } else if (id_lib != 0) {
                    editionList = editionService.findByIdLib(id_lib);
                } else if (hall_num != 0) {
                    editionList = editionService.findByHallNum(hall_num);
                } else if (rack_num != 0) {
                    editionList = editionService.findByRackNum(rack_num);
                } else if (shelf_num != 0) {
                    editionList = editionService.findByShelfNum(shelf_num);
                }
                System.out.println(editionList);
                if(editionList.size() > 0){
                    ArrayList<String[]> resultList = new ArrayList<>();
                    for(Edition edition : editionList){
                        String[] str = new String[7];
                        str[0] = String.valueOf(edition.getId_edition());
                        str[1] = String.valueOf(edition.getId_library());
                        str[2] = String.valueOf(edition.getHall_num());
                        str[3] = String.valueOf(edition.getRack_num());
                        str[4] = String.valueOf(edition.getShelf_num());
                        str[5] = String.valueOf(edition.getDate_adding());
                        if(edition.getDate_removing() == null){
                            str[6] = "<null>";
                        }else
                            str[6] = String.valueOf(edition.getDate_removing());
                        resultList.add(str);
                    }

                    searchEditionForm.updateTable(resultList);
                    searchEditionForm.setVisible(true);

                }else
                    JOptionPane.showMessageDialog(libraryInformation, "Таких книг нету!");

            });

            libraryInformation.addButton.addActionListener(f -> {
                JOptionPane.showMessageDialog(editionForm, "Вы ищите Издание, а не добавляете!");
            });
        });
   /*     libraryInformation.backButton.addActionListener(e -> {
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

                List<Information> informationList = new ArrayList<>();
                ArrayList<String[]> resultList = new ArrayList<>();
                for(int i = 0; i < 4; i++){
                    Information inf = new Information();
                    inf.setId_edition(cur_char.getId_edition());

                    inf.setAuthor("Неизвестно");
                    inf.setComposition("Неизвестно");
                    inf.setPopularity(0);
                    informationList.add(inf);
                }
                informationService.insertStartInformation(informationList);
                informationList = informationService.findByIdEdition(cur_char.getId_edition());
                for(Information inf : informationList){
                    resultList.add(new String[]{String.valueOf(inf.getId_record()), String.valueOf(inf.getId_edition()), inf.getAuthor(),inf.getComposition(), String.valueOf(inf.getPopularity())});
                }
                informationForm.updateTable(resultList);
                informationForm.setVisible(true);
            }
        });
*/
    }

    public void queryForUpdateInformation(long id_record, long id_edition, String author, String composition, int popularity) {
        Information information = new Information();
        information.setId_record(id_record);
        information.setId_edition(id_edition);
        information.setAuthor(author);
        information.setComposition(composition);
        information.setPopularity(popularity);
        informationService.update(information);
    }

    public void queryForDeleteInformation(long id_record) {
        informationService.delete(id_record);
    }

    public Information queryForInsertInformation(long id_edition, String author, String composition, int popularity){
        Information information = new Information();
        information.setId_edition(id_edition);
        information.setAuthor(author);
        information.setComposition(composition);
        information.setPopularity(popularity);
        return informationService.update(information);
    }

    public void queryForUpdateEdition(long id_edition, long id_library, int hall_num, int rack_num, int shelf_num, Date dateAdding, Date dateRemoving) {
        Edition edition = new Edition();
        edition.setId_edition(id_edition);
        edition.setId_library(id_library);
        edition.setHall_num(hall_num);
        edition.setRack_num(rack_num);
        edition.setShelf_num(shelf_num);
        edition.setDate_adding(dateAdding);
        edition.setDate_removing(dateRemoving);
        editionService.update(edition);
    }

    public void queryForDeleteEdition(long id_edition) {
        editionService.delete(id_edition);
    }

    public void queryForUpdateRule(long id_rule, long id_edition, String rule){

    }

    public void queryForDeleteRule(long id_rule){

    }

    public Rule queryForInsertRule(long id_edition, String rule){
        return null;
    }
}
