package Appli.UserInterface.Pages.ReadersPage;

import javax.swing.*;

public class ReadersForm extends JPanel {
    private JPanel readersPanel;

    public JComboBox typeComboBox;
    public JLabel nameLabel;
    public JLabel surnameLabel;
    public JLabel typeLabel;
    public JLabel libraryInfoPanel;
    public JLabel hallLabel;
    public JLabel idLabel;
    public JCheckBox OurCheckBox;
    public JCheckBox popularityCheckBox;
    public JTextField idTextField;
    public JTextField nameTextField;
    public JTextField surnameTextField;
    public JTextField textField4;
    public JTextField hallTextField;

    public ReadersForm(){
        this.add(readersPanel);
        setVisible(true);

    }
}
