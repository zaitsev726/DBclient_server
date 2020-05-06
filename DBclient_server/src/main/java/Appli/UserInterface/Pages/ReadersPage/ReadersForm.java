package Appli.UserInterface.Pages.ReadersPage;

import javax.swing.*;

public class ReadersForm extends JPanel {
    private JPanel readersPanel;
    private JLabel readersLabel;
    public JTextField nameTextField;
    public JTextField surnameTextField;
    public JTextField patronymicTextField;
    public JTextField idTextField;
    public JButton addButton;
    public JButton searchButton;
    public JButton BackButton;
    public JComboBox typeComboBox;
    private JLabel nameLabel;
    private JLabel surnameLabel;
    private JLabel patronymicLabel;
    private JLabel idLabel;
    private JLabel typeLabel;

    public ReadersForm(){
        this.add(readersPanel);
        setVisible(true);
    }

}
