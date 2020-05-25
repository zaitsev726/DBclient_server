package Application.UserInterface.Pages.IssuedPage;

import javax.swing.*;

public class IssuedForm extends JPanel{
    private JPanel issuedPanel;
    public JTextField idRecordTextField;
    public JTextField idReaderTextField;
    public JTextField idEditionTextField;
    public JTextField idLibrarianTextField;
    public JCheckBox returnedCheckBox;
    public JTextField extraditionTextField;
    public JTextField returnedTextField;
    public JCheckBox lessCheckBox;
    public JCheckBox moreCheckBox;
    public JButton searchButton;
    public JButton searchByEditionButton;
    public JButton backButton;
    public JButton librarianButton;
    private JLabel IdRecordLabel;
    private JLabel IdReaderLabel;
    private JLabel IdEditionLabel;
    private JLabel idLibrarianLabel;
    private JLabel extraditionLabel;
    private JLabel returnedLabel;
    private JButton button;


    public IssuedForm(){
        this.add(issuedPanel);
        setVisible(true);
    }

}
