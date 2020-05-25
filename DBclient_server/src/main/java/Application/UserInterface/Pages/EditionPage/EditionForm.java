package Application.UserInterface.Pages.EditionPage;

import javax.swing.*;

public class EditionForm extends JPanel {
    private JPanel editionPanel;
    public JTextField IdEditionTextField;
    public JTextField typeTextField;
    public JTextField authorTextField;
    public JTextField titleTextField;
    public JButton addButton;
    public JButton searchButton;
    public JButton informationButton;
    public JButton inventoryInformationButton;
    public JButton rulesButton;
    public JButton backButton;
    private JLabel editionLabel;
    private JLabel editionTypeLabel;
    private JLabel authorLabel;
    private JLabel titleLabel;

    public EditionForm(){
        this.add(editionPanel);
        setVisible(true);
    }
}
