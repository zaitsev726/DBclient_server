package Application.UserInterface.Pages.IssuedPage;

import Application.Entities.Librarian;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NotAttendingForm extends JPanel{
    private JPanel notAttendingPanel;
    public JButton searchButton;
    public JButton backButton;
    private JTextField startTextField;
    private JTextField endTextField;
    private JLabel startLabel;
    private JLabel endLabel;


    private Date startDate;
    private Date endDate;


    public NotAttendingForm(){
        this.add(notAttendingPanel);
        setStartValues();
        initializationListeners();
        this.setVisible(true);
    }

    private void setStartValues() {
        startDate = null;
        endDate = null;
        this.startTextField.setText("");
        this.endTextField.setText("");
    }

    private void initializationListeners() {

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
                if(endDate.getTime() < startDate.getTime()){
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

    public Date getStartDate(){return startDate;}
    public Date getEndDate(){return endDate;}
}
