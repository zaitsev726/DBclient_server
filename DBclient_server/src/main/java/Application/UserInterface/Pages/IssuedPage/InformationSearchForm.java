package Application.UserInterface.Pages.IssuedPage;

import Application.Controllers.Checker;
import Application.Entities.Librarian;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InformationSearchForm extends JPanel {
    private JTextField titleTextField;
    public JButton searchButton;
    public JButton backButton;
    private JLabel titleLabel;
    private JTextField startTextField;
    private JTextField endTextField;
    private JLabel startLabel;
    private JLabel endLabel;
    private JPanel informationPanel;


    private String title;

    private Date startDate;
    private Date endDate;

    public InformationSearchForm() {
        this.add(informationPanel);
        setStartValues();
        initializationListeners();
        setVisible(true);
    }

    private void setStartValues() {
        title = "";
        startDate = null;
        endDate = null;
        this.titleTextField.setText("");
        this.startTextField.setText("");
        this.endTextField.setText("");
    }

    private void initializationListeners() {

        this.titleTextField.addActionListener(e -> {
            if(Checker.getInstance().checkString(titleTextField.getText())){
                this.title = titleTextField.getText();
            }else{
                JOptionPane.showMessageDialog(this, "Неправильное название произведения.");
                JOptionPane.showMessageDialog(this, "Ваши значения сброшены на пустые");
                setStartValues();
            }
        });

        this.startTextField.addActionListener(e -> {
            try {
                startDate = new SimpleDateFormat("yyyy-MM-dd").parse(this.startTextField.getText());
            } catch (ParseException ignored) {
                JOptionPane.showMessageDialog(this, "Неправильный формат даты.Ожидается yyyy-MM-dd");
                JOptionPane.showMessageDialog(this, "Ваши значения сброшены на пустые");
                setStartValues();
            }
        });

        this.endTextField.addActionListener(e -> {
            try {
                endDate = new SimpleDateFormat("yyyy-MM-dd").parse(this.endTextField.getText());
                if (endDate.getTime() < startDate.getTime()) {
                    JOptionPane.showMessageDialog(this, "Конец не может быть раньше начала");
                    JOptionPane.showMessageDialog(this, "Значение конца периода сброшено на пустое");
                    endDate = null;
                    this.endTextField.setText("");
                }
            } catch (ParseException ignored) {
                JOptionPane.showMessageDialog(this, "Неправильный формат даты.Ожидается yyyy-MM-dd");
                JOptionPane.showMessageDialog(this, "Ваши значения сброшены на пустые");
                setStartValues();
            }
        });
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getTitle() {
        return title;
    }
}
