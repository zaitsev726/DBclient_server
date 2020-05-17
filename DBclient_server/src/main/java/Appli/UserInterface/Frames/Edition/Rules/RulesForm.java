package Appli.UserInterface.Frames.Edition.Rules;

import Appli.Controllers.EditionsPageController;

import javax.swing.*;

public class RulesForm extends JFrame{
    private JTextField IdTextField;
    public JButton searchButton;
    public JButton backButton;
    private JLabel idLabel;
    private JPanel rulesPanel;

    private EditionsPageController controller;

    public long id_edition;

    public RulesForm(EditionsPageController controller){
        this.controller = controller;
        setSize(400, 400);
        this.add(rulesPanel);
        setVisible(true);
        setTitle("Информация о правилах");
        setDefault();
        initializationListeners();
    }

    private void setDefault() {
        id_edition = 0;
    }

    private void initializationListeners() {
        this.IdTextField.addActionListener(e -> {
            try {
                id_edition = Long.parseLong(this.IdTextField.getText());
            }catch (NumberFormatException exception){
                JOptionPane.showMessageDialog(this, "Введите корректный номер издания");
                JOptionPane.showMessageDialog(this, "Ваши значения сброшены на пустые");
                setDefault();
            }
        });
    }


}
