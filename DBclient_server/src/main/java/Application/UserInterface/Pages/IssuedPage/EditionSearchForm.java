package Application.UserInterface.Pages.IssuedPage;

import Application.Controllers.Checker;

import javax.swing.*;

public class EditionSearchForm extends JPanel {
    private JPanel editionSearchPanel;
    private JTextField titleTextField;
    private JTextField typeTextField;
    public JButton searchButton;
    public JButton backButton;
    private JLabel infoLabel;
    private JLabel titleLabel;
    private JLabel typeLabel;

    private String title;
    private String type ;

    public EditionSearchForm(){
        this.add(editionSearchPanel);
        initializationListeners();
        setStartValues();
        setVisible(true);
    }

    public void setStartValues(){
        title = "";
        type = "";
        this.titleTextField.setText("");
        this.typeTextField.setText("");

    }
    private void initializationListeners() {
        this.titleTextField.addActionListener(e -> {
            if(Checker.getInstance().checkString(titleTextField.getText())){
                title = titleTextField.getText();
            }else{
                JOptionPane.showMessageDialog(this, "Введите корректное название");
                JOptionPane.showMessageDialog(this, "Ваши значения сброшены на нулевые");
                setStartValues();
            }
        });

        this.typeTextField.addActionListener(e -> {
            if(Checker.getInstance().checkString(typeTextField.getText())){
                type = typeTextField.getText();
            }else{
                JOptionPane.showMessageDialog(this, "Введите корректное тип произведения");
                JOptionPane.showMessageDialog(this, "Ваши значения сброшены на нулевые");
                setStartValues();
            }
        });
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }
}
