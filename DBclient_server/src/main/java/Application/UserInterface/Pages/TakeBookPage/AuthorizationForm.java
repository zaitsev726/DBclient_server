package Application.UserInterface.Pages.TakeBookPage;

import javax.swing.*;

public class AuthorizationForm extends JPanel{
    private JPanel authorizationPanel;
    private JTextField IdTextField;
    public JButton continueButton;
    public JButton backButton;
    private JLabel idLabel;

    public long id_reader;
    public AuthorizationForm(){
        this.add(authorizationPanel);
        initializationListener();
        setVisible(true);
    }

    private void setStartValues(){
        id_reader = 0;
    }

    private void initializationListener(){
        this.IdTextField.addActionListener(e -> {
            try {
                id_reader = Long.parseLong(this.IdTextField.getText());
            } catch (NumberFormatException ignored) {
                JOptionPane.showMessageDialog(this, "Введите корректный ID читателя");
                JOptionPane.showMessageDialog(this, "Ваши значения сброшены на нулевые");
                setStartValues();
            }
        });
    }
}
