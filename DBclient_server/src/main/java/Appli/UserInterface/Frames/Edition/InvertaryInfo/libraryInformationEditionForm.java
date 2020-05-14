package Appli.UserInterface.Frames.Edition.InvertaryInfo;

import Appli.Controllers.EditionsPageController;
import Appli.Entities.AllReader;
import Appli.Entities.Library;
import Appli.UserInterface.Frames.Library.SearchReadersInLibraryForm;

import javax.persistence.NoResultException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class libraryInformationEditionForm extends JFrame {
    private JPanel informationPanel;
    private JTextField IdLibraryTextField;
    private JTextField hallNumTextField;
    private JTextField rackNumTextField;
    private JTextField shelfNumTextField;
    private JCheckBox checkBox1;
    public JButton addButton;
    public JButton searchButton;
    public JButton backButton;
    private JLabel IdLibraryLabel;
    private JLabel hallNumLabel;
    private JLabel rackNumLabel;
    private JLabel shelfNumLabel;
    private JLabel currentDateLabel;
    private JTextField textField1;
    private JTextField textField2;

    public long IdLib = 0;
    public int hallNum = 0;
    public int rackNum = 0;
    public int shelfNum = 0;
    public boolean curDate = false;
    public Date preferDate = null;

    private EditionsPageController controller;
    public libraryInformationEditionForm(EditionsPageController controller){
        this.controller = controller;
        setSize(600, 400);
        this.add(informationPanel);
        setVisible(true);
        setTitle("Информация о издании");
        setDefault();
        initializationListeners();
    }

    private void setDefault() {
        IdLib = 0;
        hallNum = 0;
        rackNum = 0;
        shelfNum = 0;
        curDate = false;
    }

    private void initializationListeners() {

        this.IdLibraryTextField.addActionListener(e -> {
            try {
                IdLib = Long.parseLong(this.IdLibraryTextField.getText());
            }catch (NumberFormatException exception){
                JOptionPane.showMessageDialog(this, "Введите корректный номер библиотеки");
            }
        });

        this.hallNumTextField.addActionListener(e -> {
            try {
                hallNum = Integer.parseInt(this.hallNumTextField.getText());
            }catch (NumberFormatException exception){
                JOptionPane.showMessageDialog(this, "Введите корректный номер зала");
            }
        });

        this.rackNumTextField.addActionListener(e -> {
            try {
                rackNum = Integer.parseInt(this.rackNumTextField.getText());
            }catch (NumberFormatException exception){
                JOptionPane.showMessageDialog(this, "Введите корректный номер стеллажа");
            }
        });

        this.shelfNumTextField.addActionListener(e -> {
            try {
                shelfNum = Integer.parseInt(this.shelfNumTextField.getText());
            }catch (NumberFormatException exception){
                JOptionPane.showMessageDialog(this, "Введите корректный номер полки");
            }
        });



    }


}
