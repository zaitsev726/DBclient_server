package Appli.UserInterface.Pages.EditionPage;

import javax.swing.*;

public class EditionForm extends JPanel {
    private JPanel editionPanel;
    public JTextField IdEditionTextField;
    public JTextField typeTextField;
    public JTextField authorTextField;
    public JTextField titleTextField;
    private JSpinner spinner1;
    private JSpinner spinner2;
    private JButton button1;
    public JButton addButton;
    public JButton searchButton;
    public JButton informationButton;
    private JButton button5;
    private JButton правилаButton;
    private JButton button7;
    private JButton button8;
    private JLabel editionLabel;
    private JLabel idEditionLabel;
    private JLabel editionTypeLabel;
    private JLabel authorLabel;
    private JLabel titleLabel;

    public EditionForm(){
        this.add(editionPanel);
        setVisible(true);
    }
}
