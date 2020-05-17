package Appli.UserInterface.Frames.Edition.Information;

import Appli.Controllers.Checker;
import Appli.Controllers.EditionsPageController;

import javax.swing.*;

public class InformationForm extends JFrame{
    private JPanel informationPanel;
    private JTextField IDTextField;
    private JTextField authorTextField;
    private JTextField compositionTextField;
    public JButton mostPopularButton;
    public JButton searchButton;
    public JButton backButton;
    private JTextField popularityTextField;
    private JCheckBox popularityCheckBox;
    private JLabel IdLabel;
    private JLabel authorLabel;
    private JLabel compositionLabel;
    private JLabel popularityLabel;

    public long Id_edition;
    public String author;
    public String composition;
    public int popularity;
    public boolean morePopular;

    private EditionsPageController controller;
    public InformationForm(EditionsPageController controller){
        this.controller = controller;
        setSize(800, 400);
        this.add(informationPanel);
        setVisible(true);
        setTitle("Информация о издании");
        setDefault();
        initializationListeners();
    }

    private void setDefault() {
        Id_edition = 0L;
        author = "";
        composition = "";
        popularity = 0;
        morePopular = false;

    }

    private void initializationListeners() {
        this.IDTextField.addActionListener(e -> {
            try {
                Id_edition = Long.parseLong(this.IDTextField.getText());
            }catch (NumberFormatException exception){
                JOptionPane.showMessageDialog(this, "Введите корректный номер библиотеки");
            }
        });

        this.authorTextField.addActionListener(e -> {
           if(Checker.getInstance().checkString(authorTextField.getText())){
                author = authorTextField.getText();
           }else
               JOptionPane.showMessageDialog(this, "Введите корректное имя автора");
        });

        this.compositionTextField.addActionListener(e -> {
            if(Checker.getInstance().checkString(compositionTextField.getText())){
                composition = compositionTextField.getText();
            }else
                JOptionPane.showMessageDialog(this, "Введите корректное название произведение");
        });

        this.popularityTextField.addActionListener(e -> {
            try {
                popularity = Integer.parseInt(this.popularityTextField.getText());
            }catch (NumberFormatException exception){
                JOptionPane.showMessageDialog(this, "Введите корректный номер библиотеки");
            }
        });

        this.popularityCheckBox.addActionListener(e -> {
            morePopular = popularityCheckBox.isSelected();
        });
    }
}
