package Appli.UserInterface.Pages.IssuedPage;

import javax.swing.*;

public class IssuedForm extends JPanel{
    private JPanel issuedPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JCheckBox returnedCheckBox;
    private JTextField extraditionTextField;
    private JTextField returnedTextField;
    private JCheckBox checkBox2;
    private JCheckBox checkBox3;
    public JButton searchButton;
    public JButton backButton;
    private JLabel IdRecordLabel;
    private JLabel IdReaderLabel;
    private JLabel IdEditionLabel;
    private JLabel idLibrarianLabel;
    private JLabel extraditionLabel;
    private JLabel returnedLabel;

    public IssuedForm(){
        this.add(issuedPanel);
        setVisible(true);
    }
}
