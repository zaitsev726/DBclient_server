package Application.UserInterface.Frames.Edition.InvertaryInfo;

import Application.Controllers.EditionsPageController;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InventoryInformationForm extends JFrame {
    private JPanel informationPanel;
    private JTextField IdLibraryTextField;
    private JTextField hallNumTextField;
    private JTextField rackNumTextField;
    private JTextField shelfNumTextField;
    private JCheckBox curDateCheckBox;
    public JButton addButton;
    public JButton searchButton;
    public JButton backButton;
    private JLabel IdLibraryLabel;
    private JLabel hallNumLabel;
    private JLabel rackNumLabel;
    private JLabel shelfNumLabel;
    private JLabel currentDateLabel;
    private JTextField dateAddingTextField;
    private JTextField dateRemovingTextField;
    private JCheckBox lessCheckBox;
    private JCheckBox moreCheckBox;
    private JTextField IdEditionTextField;
    private JLabel idEdition;

    public long IdEdition = 0;
    public long IdLib = 0;
    public int hallNum = 0;
    public int rackNum = 0;
    public int shelfNum = 0;
    public boolean curDate = false;
    public Date dateAdding = null;
    public boolean lessThenAdding = false;
    public Date dateRemoving = null;
    public boolean moreThenRemoving = false;



    private EditionsPageController controller;
    public InventoryInformationForm(EditionsPageController controller){
        this.controller = controller;
        setSize(800, 600);
        this.add(informationPanel);
        setVisible(true);
        setTitle("Информация о издании");
        setDefault();
        initializationListeners();
    }

    private void setDefault() {
        IdEdition = 0;
        IdLib = 0;
        hallNum = 0;
        rackNum = 0;
        shelfNum = 0;
        curDate = false;
        dateAdding = null;
        lessThenAdding = false;
        dateRemoving = null;
        moreThenRemoving = false;

    }

    private void initializationListeners() {

        this.IdEditionTextField.addActionListener(e -> {
            try {
                IdEdition = Long.parseLong(this.IdEditionTextField.getText());
            }catch (NumberFormatException exception){
                JOptionPane.showMessageDialog(this, "Введите корректный номер библиотеки");
                JOptionPane.showMessageDialog(this, "Ваши значения сброшены на пустые");
                setDefault();
            }
        });

        this.IdLibraryTextField.addActionListener(e -> {
            try {
                IdLib = Long.parseLong(this.IdLibraryTextField.getText());
            }catch (NumberFormatException exception){
                JOptionPane.showMessageDialog(this, "Введите корректный номер библиотеки");
                JOptionPane.showMessageDialog(this, "Ваши значения сброшены на пустые");
                setDefault();
            }
        });

        this.hallNumTextField.addActionListener(e -> {
            try {
                hallNum = Integer.parseInt(this.hallNumTextField.getText());
            }catch (NumberFormatException exception){
                JOptionPane.showMessageDialog(this, "Введите корректный номер зала");
                JOptionPane.showMessageDialog(this, "Ваши значения сброшены на пустые");
                setDefault();
            }
        });

        this.rackNumTextField.addActionListener(e -> {
            try {
                rackNum = Integer.parseInt(this.rackNumTextField.getText());
            }catch (NumberFormatException exception){
                JOptionPane.showMessageDialog(this, "Введите корректный номер стеллажа");
                JOptionPane.showMessageDialog(this, "Ваши значения сброшены на пустые");
                setDefault();
            }
        });

        this.shelfNumTextField.addActionListener(e -> {
            try {
                shelfNum = Integer.parseInt(this.shelfNumTextField.getText());
            }catch (NumberFormatException exception){
                JOptionPane.showMessageDialog(this, "Введите корректный номер полки");
                JOptionPane.showMessageDialog(this, "Ваши значения сброшены на пустые");
                setDefault();
            }
        });

        this.curDateCheckBox.addActionListener(e -> {
            curDate = curDateCheckBox.isSelected();
        });

        this.dateAddingTextField.addActionListener(e ->{
            try {
                dateAdding = new SimpleDateFormat("yyyy-MM-dd").parse(dateAddingTextField.getText());
            } catch (ParseException ignored) {
                JOptionPane.showMessageDialog(this, "Неправильный формат даты.Ожидается yyyy-MM-dd");
                JOptionPane.showMessageDialog(this, "Ваши значения сброшены на пустые");
                setDefault();
            };
        });

        this.dateRemovingTextField.addActionListener(e ->{
            try {
                dateRemoving = new SimpleDateFormat("yyyy-MM-dd").parse(dateRemovingTextField.getText());
            } catch (ParseException ignored) {
                JOptionPane.showMessageDialog(this, "Неправильный формат даты.Ожидается yyyy-MM-dd");
                JOptionPane.showMessageDialog(this, "Ваши значения сброшены на пустые");
                setDefault();
            };
        });

        this.lessCheckBox.addActionListener(e -> {
            lessThenAdding = lessCheckBox.isSelected();
        });

        this.moreCheckBox.addActionListener(e -> {
            moreThenRemoving = moreCheckBox.isSelected();
        });
    }


}
