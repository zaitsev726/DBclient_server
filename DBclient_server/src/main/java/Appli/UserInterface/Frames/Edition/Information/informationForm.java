package Appli.UserInterface.Frames.Edition.Information;

import Appli.Controllers.EditionsPageController;

import javax.swing.*;

public class informationForm extends JFrame{
    private JPanel informationPanel;
    private JTextField IDTextField;
    private JTextField authorTextField;
    private JTextField compositionTextField;
    private JButton найтиСамоеПопулярноеButton;
    private JButton найтиButton;
    private JButton назадButton;
    private JTextField popularityTextField;
    private JCheckBox checkBox1;
    private JLabel IdLabel;
    private JLabel authorLabel;
    private JLabel compositionLabel;
    private JLabel popularityLabel;

    private EditionsPageController controller;
    public informationForm(EditionsPageController controller){
        this.controller = controller;
        setSize(600, 400);
        this.add(informationPanel);
        setVisible(true);
        setTitle("Информация о издании");
        setDefault();
        initializationListeners();
    }

    private void setDefault() {

    }

    private void initializationListeners() {

    }
}
