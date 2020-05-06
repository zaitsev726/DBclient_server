package Appli.UserInterface.Frames;

import javax.swing.*;

public class ProfessionForm extends JFrame {
    private JPanel professionPanel;
    private JPanel pensionerPanel;
    private JPanel schoolkidPanel;
    private JPanel scientistPanel;
    private JPanel studentPanel;
    private JPanel teacherPanel;
    private JPanel workerPanel;
    private JTextField pensionerTextField;
    private JLabel numberPensionerLabel;
    private JTextField schoolTextField;
    private JTextField gradeTextField;
    private JLabel schoolnumberLabel;
    private JLabel gradeLabel;
    private JButton pContinueButton;
    private JButton pBackButton;
    private JButton scContinueutton;
    private JButton scBackButton;
    private JTextField sUniversityTextField;
    private JTextField sGroupTextField;
    private JTextField sFacultyTextField;
    private JButton sContinueButton;
    private JButton sBackButton;
    private JTextField sciAddresTextField;
    private JTextField sciUniversityTextField;
    private JButton sciContinueButton;
    private JButton sciBackButton;
    private JLabel sUniversityLabel;
    private JLabel sFacultyLabel;
    private JLabel sGroupLabel;
    private JLabel scitAddressLabel;
    private JLabel sciUniversityLabel;
    private JPanel currentPanel;
    public ProfessionForm(){
        setTitle("Информация о профессии");
        setSize(400, 400);
      //  setVisible(true);
        this.add(professionPanel);
        currentPanel = pensionerPanel;
    }

    public void changePanel(String type){

        this.remove(currentPanel);
        switch (type){
            case ("pensioner"):
                this.add(pensionerPanel);
                currentPanel = pensionerPanel;
                break;
            case ("schoolkid"):
                this.add(schoolkidPanel);
                currentPanel = schoolkidPanel;
                break;
            case ("scientist"):
                this.add(scientistPanel);
                currentPanel = scientistPanel;
                break;
            case ("student"):
                this.add(studentPanel);
                currentPanel = studentPanel;
                break;
            case ("teacher"):
                this.add(teacherPanel);
                currentPanel = teacherPanel;
                break;
            case ("worker"):
                this.add(workerPanel);
                currentPanel = workerPanel;
                break;

            default:
                JOptionPane.showMessageDialog(this, "Ошибка");
                break;

        }

        this.revalidate();
        this.repaint();

    }
}
