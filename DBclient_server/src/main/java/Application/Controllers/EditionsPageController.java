package Application.Controllers;

import Application.Entities.*;
import Application.Services.*;
import Application.Services.Impl.*;
import Application.UserInterface.Frames.Edition.Characteristic.CharacteristicTable;
import Application.UserInterface.Frames.Edition.Information.InformationTable;
import Application.UserInterface.Frames.Edition.Information.InformationForm;
import Application.UserInterface.Frames.Edition.InvertaryInfo.EditionTable;
import Application.UserInterface.Frames.Edition.InvertaryInfo.RulesInEdition;
import Application.UserInterface.Frames.Edition.InvertaryInfo.InventoryInformationForm;
import Application.UserInterface.Frames.Edition.Rules.RulesForm;
import Application.UserInterface.Frames.Edition.Rules.RulesTable;
import Application.UserInterface.Pages.EditionPage.EditionForm;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class EditionsPageController {
    private EditionForm editionForm;

    private CharacteristicService charService;
    private EditionService editionService;
    private InformationService informationService;
    private LibraryService libraryService;
    private RuleService ruleService;
    private IssuedBookService issuedBookService;


    private String cur_type;
    private String cur_author;
    private String cur_title;
    private Characteristic cur_char;

    private InventoryInformationForm inventoryLibraryInformation;
    private InformationForm informationForm;
    private RulesForm rulesForm;

    private InformationTable informationTable;
    private EditionTable editionTable;
    private RulesTable rulesTable;
    private CharacteristicTable characteristicTable;


    public EditionsPageController(EditionForm editionForm, CharacteristicService characteristicService, EditionService editionService, InformationService informationService,
                                  LibraryService libraryService, RuleService ruleService, IssuedBookService issuedBookService) {
        this.editionForm = editionForm;

        this.informationTable = new InformationTable(this);
        this.editionTable = new EditionTable(this);
        this.rulesTable = new RulesTable(this);
        this.characteristicTable = new CharacteristicTable(this);

        this.charService = characteristicService;
        this.editionService = editionService;
        this.informationService = informationService;
        this.libraryService = libraryService;
        this.ruleService = ruleService;
        this.issuedBookService = issuedBookService;

        setStartValues();
        initializationListeners();
    }

    private void setStartValues() {
        cur_type = "";
        cur_author = "";
        cur_title = "";
        editionForm.typeTextField.setText("");
        editionForm.authorTextField.setText("");
        editionForm.titleTextField.setText("");

    }

    private void initializationListeners() {

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

        /**
         * Добавляем новое издание. Сначала происходит добавление издания в Characteristic (т.е. характериситики издания)
         * Это его "физическая" часть, т.е. как оно выглядит в жизни. Например, книга "Сборник стихотворений А.С.Пушкина".
         * В Characteristic будут его физические характеристики, т.е.данные о том, что это СБОРНИК, данные о том, кто его написал.
         * НЕ БУДЕТ информации о его содержимом и информации про библиотеку
         * Затем вводятся информация о БИБЛИОТЕКЕ, в которой лежит издание
         * И затем вводятся 5 произведений "по умолчанию", которые можно менять
         */
        editionForm.addButton.addActionListener(e -> {
            if (!cur_type.equals("") && !cur_author.equals("") && !cur_title.equals("")) {
                Characteristic characteristic = new Characteristic();
                characteristic.setType_edition(cur_type);
                characteristic.setAuthor(cur_author);
                characteristic.setTitle(cur_title);
                cur_char = charService.save(characteristic);
                if (cur_char.getId_edition() != null && cur_char.getId_edition() != 0) {
                    inventoryLibraryInformation = new InventoryInformationForm(this);

                    inventoryLibraryInformation.backButton.addActionListener(f -> {
                        charService.delete(cur_char.getId_edition());
                        cur_char = new Characteristic();
                        setStartValues();
                        inventoryLibraryInformation.dispose();
                        inventoryLibraryInformation = null;
                    });

                    inventoryLibraryInformation.addButton.addActionListener(f -> {
                        if (inventoryLibraryInformation.IdLib != 0 && inventoryLibraryInformation.hallNum != 0 && inventoryLibraryInformation.rackNum != 0
                                && inventoryLibraryInformation.shelfNum != 0) {
                            Edition edition = new Edition();
                            edition.setId_edition(cur_char.getId_edition());
                            edition.setId_library(inventoryLibraryInformation.IdLib);
                            edition.setHall_num(inventoryLibraryInformation.hallNum);
                            edition.setRack_num(inventoryLibraryInformation.rackNum);
                            edition.setShelf_num(inventoryLibraryInformation.shelfNum);
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
                            informationTable.updateTable(resultList);
                            informationTable.setVisible(true);

                            inventoryLibraryInformation.dispose();
                            inventoryLibraryInformation = null;

                        }
                    });

                    inventoryLibraryInformation.searchButton.addActionListener(f -> {
                        JOptionPane.showMessageDialog(editionForm, "Вы добавляете Издание, а не ищите!");
                    });
                }
            } else {
                JOptionPane.showMessageDialog(editionForm, "Вы ввели не все параметры");
            }
            setStartValues();
        });

        editionForm.searchButton.addActionListener(e -> {
            List<Characteristic> characteristicList = new ArrayList<>();
            if (cur_type.equals("") && cur_author.equals("") && cur_title.equals("")) {
                characteristicList = charService.findAll();
            } else if (!cur_type.equals("") && !cur_author.equals("") && !cur_title.equals("")) {
                characteristicList = charService.findByTypeAndAuthorAndTitle(cur_type, cur_author, cur_title);
            } else if (!cur_type.equals("") && !cur_author.equals("")) {
                characteristicList = charService.findByTypeAndAuthor(cur_type, cur_author);
            } else if (!cur_type.equals("") && !cur_title.equals("")) {
                characteristicList = charService.findByTypeAndTitle(cur_type, cur_title);
            } else if (!cur_author.equals("") && !cur_title.equals("")) {
                characteristicList = charService.findByAuthorAndTitle(cur_author, cur_title);
            } else if (!cur_type.equals("")) {
                characteristicList = charService.findByType(cur_type);
            } else if (!cur_author.equals("")) {
                characteristicList = charService.findByAuthor(cur_author);
            } else if (!cur_title.equals("")) {
                characteristicList = charService.findByTitle(cur_title);
            }

            if (characteristicList.size() > 0) {
                ArrayList<String[]> resultList = new ArrayList<>();
                for (Characteristic characteristic : characteristicList) {

                    resultList.add(new String[]{String.valueOf(characteristic.getId_edition()), characteristic.getType_edition(),
                            characteristic.getAuthor(), characteristic.getTitle()});
                }
                characteristicTable.updateTable(resultList);
                characteristicTable.setVisible(true);

            } else
                JOptionPane.showMessageDialog(editionForm, "Таких изданий нету!");
        });
        /*
          Поиск Инвентраной информации, т.е. библиотечной информации про издание ( полка, шкаф и т.д.)
         */
        editionForm.inventoryInformationButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(editionForm, "Информация на данной странице никак не учитывается!");
            inventoryLibraryInformation = new InventoryInformationForm(this);

            inventoryLibraryInformation.backButton.addActionListener(f -> {
                setStartValues();
                inventoryLibraryInformation.dispose();
                inventoryLibraryInformation = null;
            });

            inventoryLibraryInformation.searchButton.addActionListener(f -> {
                List<Edition> editionList = new ArrayList<>();
                long id_edition = inventoryLibraryInformation.IdEdition;
                long id_lib = inventoryLibraryInformation.IdLib;
                int hall_num = inventoryLibraryInformation.hallNum;
                int rack_num = inventoryLibraryInformation.rackNum;
                int shelf_num = inventoryLibraryInformation.shelfNum;
                Date adding = inventoryLibraryInformation.dateAdding;
                Date removing = inventoryLibraryInformation.dateRemoving;

                if (id_edition != 0) {
                    editionList.add(editionService.findById(id_edition));
                } else if (id_lib == 0 && hall_num == 0 && rack_num == 0 && shelf_num == 0 && adding == null && removing == null) {
                    editionList = editionService.findAll();
                } else if (id_lib == 0 && hall_num == 0 && rack_num == 0 && shelf_num == 0) {
                    if (adding != null && removing != null) {
                        editionList = editionService.findByDateAddingAndDateRemoving(adding, removing);
                    } else if (adding != null) {
                        if (!inventoryLibraryInformation.lessThenAdding)
                            editionList = editionService.findByMoreDateAdding(adding);
                        else
                            editionList = editionService.findByLessDateAdding(adding);
                    } else if (removing != null) {
                        if (!inventoryLibraryInformation.moreThenRemoving)
                            editionList = editionService.findByLessDateRemoving(removing);
                        else
                            editionList = editionService.findByMoreDateRemoving(removing);
                    }
                } else if (id_lib != 0 && hall_num != 0 && rack_num != 0 && shelf_num != 0) {
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
                    editionList = editionService.findByHallNumAndRackNum(hall_num, rack_num);
                } else if (hall_num != 0 && shelf_num != 0) {
                    editionList = editionService.findByHallNumAndShelfNum(hall_num, shelf_num);
                } else if (rack_num != 0 && shelf_num != 0) {
                    editionList = editionService.findByRackNumAndShelfNum(rack_num, shelf_num);
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
                if (adding != null && removing == null) {
                    Iterator<Edition> iterator = editionList.iterator();
                    while (iterator.hasNext()) {
                        Edition edition = iterator.next();
                        if (!inventoryLibraryInformation.lessThenAdding) {
                            if (edition.getDate_adding().getTime() < adding.getTime())
                                iterator.remove();
                        } else {
                            if (edition.getDate_adding().getTime() >= adding.getTime())
                                iterator.remove();
                        }
                    }
                }

                if (removing != null && adding == null) {
                    Iterator<Edition> iterator = editionList.iterator();
                    while (iterator.hasNext()) {
                        Edition edition = iterator.next();
                        if (edition.getDate_removing() != null) {
                            if (!inventoryLibraryInformation.moreThenRemoving) {
                                if (edition.getDate_removing().getTime() > removing.getTime())
                                    iterator.remove();
                            } else {
                                if (edition.getDate_removing().getTime() <= removing.getTime())
                                    iterator.remove();
                            }
                        } else
                            iterator.remove();
                    }
                }

                if (editionList.size() > 0) {
                    ArrayList<String[]> resultList = new ArrayList<>();
                    for (Edition edition : editionList) {
                        String[] str = new String[7];
                        str[0] = String.valueOf(edition.getId_edition());
                        str[1] = String.valueOf(edition.getId_library());
                        str[2] = String.valueOf(edition.getHall_num());
                        str[3] = String.valueOf(edition.getRack_num());
                        str[4] = String.valueOf(edition.getShelf_num());
                        str[5] = (edition.getDate_adding().getYear() + 1900) + "-" + edition.getDate_adding().getMonth() + "-" + edition.getDate_adding().getDate();
                        if (edition.getDate_removing() == null) {
                            str[6] = "<null>";
                        } else
                            str[6] = (edition.getDate_removing().getYear() + 1900) + "-" + edition.getDate_removing().getMonth() + "-" + edition.getDate_removing().getDate();
                        resultList.add(str);
                    }

                    editionTable.updateTable(resultList);
                    editionTable.setVisible(true);

                } else
                    JOptionPane.showMessageDialog(inventoryLibraryInformation, "Таких книг нету!");

            });

            inventoryLibraryInformation.addButton.addActionListener(f -> {
                JOptionPane.showMessageDialog(editionForm, "Вы ищите Издание, а не добавляете!");
            });
        });

        editionForm.informationButton.addActionListener(e ->
        {
            JOptionPane.showMessageDialog(editionForm, "Информация на данной странице никак не учитывается!");
            informationForm = new InformationForm(this);

            informationForm.backButton.addActionListener(f -> {
                setStartValues();
                informationForm.dispose();
                informationForm = null;
            });

            informationForm.searchButton.addActionListener(f -> {
                long id_edition = informationForm.Id_edition;
                String author = informationForm.author;
                String composition = informationForm.composition;
                int popularity = informationForm.popularity;
                List<Information> informationList = new ArrayList<>();

                if (id_edition == 0 && author.equals("") && composition.equals("") && popularity == 0) {
                    informationList = informationService.findAll();
                } else if (!author.equals("") && !composition.equals("")) {
                    informationList = informationService.findByAuthorAndComposition(author, composition);
                } else if (id_edition != 0) {
                    informationList = informationService.findByIdEdition(id_edition);
                } else if (!author.equals("")) {
                    informationList = informationService.findByAuthor(author);
                } else if (!composition.equals("")) {
                    informationList = informationService.findByComposition(composition);
                }

                if (popularity != 0) {
                    Iterator<Information> iterator = informationList.iterator();
                    while (iterator.hasNext()) {
                        Information information = iterator.next();
                        if (informationForm.morePopular)
                            if (information.getPopularity() <= popularity)
                                iterator.remove();
                            else if (information.getPopularity() > popularity)
                                iterator.remove();
                    }
                }

                if (informationList.size() > 0) {
                    ArrayList<String[]> resultList = new ArrayList<>();
                    for (Information information : informationList) {
                        resultList.add(new String[]{String.valueOf(information.getId_record()), String.valueOf(information.getId_edition()), information.getAuthor(),
                                information.getComposition(), String.valueOf(information.getPopularity())});
                    }

                    informationTable.updateTable(resultList);
                    informationTable.setVisible(true);

                } else
                    JOptionPane.showMessageDialog(inventoryLibraryInformation, "Таких произведений нету!");


            });
            informationForm.mostPopularButton.addActionListener(f -> {
                Information information = informationService.mostPopular();
                JOptionPane.showMessageDialog(informationForm,
                        new String[]{"Самое популярное произведение",
                                " Автор: " + information.getAuthor(),
                                " Название: " + information.getComposition(),
                                " Популярность: " + information.getPopularity()}, "Популярнсоть", JOptionPane.INFORMATION_MESSAGE);
            });

        });

        editionForm.rulesButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(editionForm, "Информация на данной странице никак не учитывается!");
            rulesForm = new RulesForm(this);

            rulesForm.backButton.addActionListener(f -> {
                setStartValues();
                rulesForm.dispose();
                rulesForm = null;
            });

            rulesForm.searchButton.addActionListener(f -> {
                long id_edition = rulesForm.id_edition;
                List<Rule> rulesList = new ArrayList<>();
                if (id_edition == 0) {
                    rulesList = ruleService.findAll();
                } else
                    rulesList = ruleService.findByIdEdition(id_edition);

                if (rulesList.size() > 0) {
                    ArrayList<String[]> resultList = new ArrayList<>();
                    for (Rule rule : rulesList) {
                        resultList.add(new String[]{String.valueOf(rule.getId_rule()), String.valueOf(rule.getId_edition()), rule.getRule()});
                    }

                    rulesTable.updateTable(resultList);
                    rulesTable.setVisible(true);

                } else
                    JOptionPane.showMessageDialog(inventoryLibraryInformation, "У этого издания нет правил!");
            });

        });

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

    public Information queryForInsertInformation(long id_edition, String author, String composition, int popularity) {
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

    public void queryForUpdateRule(long id_rule, long id_edition, String str_rule) {
        Rule rule = new Rule();
        rule.setId_rule(id_rule);
        rule.setId_edition(id_edition);
        rule.setRule(str_rule);
        ruleService.update(rule);
    }

    public void queryForDeleteRule(long id_rule) {
        ruleService.delete(id_rule);
    }

    public Rule queryForInsertRule(long id_edition, String str_rule) {
        Rule rule = new Rule();
        rule.setId_edition(id_edition);
        rule.setRule(str_rule);
        return ruleService.update(rule);
    }

    public void showCurrentLibrary(long id_library) {
        Library library = libraryService.getById(id_library);
        JOptionPane.showMessageDialog(editionTable,
                new String[]{"Информация о библиотеке",
                        " ID библиотеки: " + library.getId_library(),
                        " Количество залов: " + library.getHalls_num(),
                        " Количество книг: " + library.getEditions().size(),
                        " Количество зарегистрированных читателей: " + library.getReaders().size()}, "Библиотека", JOptionPane.INFORMATION_MESSAGE);

    }

    public void showCurrentRules(long id_edition) {
        List<Rule> rules = ruleService.findByIdEdition(id_edition);
        RulesInEdition rulesInEditionForm = new RulesInEdition(rules);
    }

    public void queryForUpdateTable(ArrayList<String[]> currentInventoryInfo) {
        currentInventoryInfo.removeIf(next -> issuedBookService.isReturned(Long.valueOf(next[0])));
        editionTable.updateTable(currentInventoryInfo);
    }

    public void queryForUpdateCharacteristic(long id_edition, String type, String author, String title) {
        Characteristic characteristic = new Characteristic();
        characteristic.setId_edition(id_edition);
        characteristic.setType_edition(type);
        characteristic.setAuthor(author);
        characteristic.setTitle(title);
        charService.update(characteristic);
    }

    public void queryForDeleteCharacteristic(long id_edition) {
        charService.delete(id_edition);
    }

    public void queryForShowEdition(long id_edition) {
        Characteristic characteristic = charService.findById(id_edition);
        JOptionPane.showMessageDialog(editionTable,
                new String[]{"Информация о издании",
                        " ID издания: " + characteristic.getId_edition(),
                        " Тип издания: " + characteristic.getType_edition(),
                        " Автор издания: " + characteristic.getAuthor(),
                        " Название издания: " + characteristic.getTitle()}, "Библиотека", JOptionPane.INFORMATION_MESSAGE);

    }
}
