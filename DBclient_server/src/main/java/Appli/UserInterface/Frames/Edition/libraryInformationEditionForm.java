package Appli.UserInterface.Frames.Edition;

import Appli.Controllers.EditionsPageController;

import javax.swing.*;

public class libraryInformationEditionForm extends JFrame {
    private JPanel informationPanel;
    private JTextField IdLibraryTextField;
    private JTextField hallNumTextField;
    private JTextField rackNumTextField;
    private JTextField shelfNumTextField;
    private JCheckBox checkBox1;
    private JButton addButton;
    private JButton backButton;
    private JLabel IdLibraryLabel;
    private JLabel hallNumLabel;
    private JLabel rackNumLabel;
    private JLabel shelfNumLabel;
    private JLabel currentDateLabel;

    private EditionsPageController controller;
    public libraryInformationEditionForm(EditionsPageController controller){
        this.controller = controller;
        setSize(600, 400);
        this.add(informationPanel);
        setVisible(true);
        setTitle("Информация о издании");
    }


}
