package Appli.UserInterface.Frames;

import Appli.Controllers.Checker;
import Appli.Controllers.ReadersPageController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
    private JTextField sFacultyTextField;
    private JTextField sGroupTextField;
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
    private JTextField tUniversityTextField;
    private JTextField tFacultyTextField;
    private JButton tContinueButton;
    private JButton tBackButton;
    private JLabel tUniversityLabel;
    private JLabel tFacultyLabel;
    private JTextField wAddresTextField;
    private JTextField wFirmTextField;
    private JButton wContinueButton;
    private JButton wBackButton;
    private JLabel wAddresLabel;
    private JLabel wFirmLabel;
    private JPanel currentPanel;

    private ArrayList<String> currentParam;
    private ReadersPageController controller;

    public ProfessionForm(ReadersPageController controller) {
        this.controller = controller;
       // this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setTitle("Информация о профессии");
        setSize(400, 400);
        //  setVisible(true);
        currentParam = new ArrayList<>();
        initializationListeners();
        this.add(professionPanel);
        currentPanel = pensionerPanel;

        pContinueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public void changePanel(String type) {
        currentParam = new ArrayList<>();
        this.remove(currentPanel);
        switch (type) {
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

    private void initializationListeners() {
        /**пенсионер*/
        pensionerTextField.addActionListener(e -> {
            try {
                long id_library = Long.parseLong(pensionerTextField.getText());
                if(id_library > 0)
                    currentParam.add(String.valueOf(id_library));
                else
                    JOptionPane.showMessageDialog(pensionerPanel, "Введите положительный номер");

            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(pensionerPanel, "Введите корректный номер");
            }
        });

        pContinueButton.addActionListener(e -> {
            if(currentParam.size() == 1)
                controller.setParam( currentParam, "pensioner");
            else
                JOptionPane.showMessageDialog(pensionerPanel, "Вы ввели не все параметры");
            currentParam = new ArrayList<>();
            this.dispose();
        });

        pBackButton.addActionListener(e -> {
            this.dispose();
            currentParam = new ArrayList<>();
        });

        /**школьник*/
        schoolTextField.addActionListener(e -> {
            try {
                long id_school = Long.parseLong(schoolTextField.getText());
                if(id_school > 0)
                    currentParam.add(String.valueOf(id_school));
                else
                    JOptionPane.showMessageDialog(schoolkidPanel, "Введите положительный номер школы");

            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(schoolkidPanel, "Введите корректный номер школы");
            }
        });

        gradeTextField.addActionListener(e -> {
            try {
                long grade = Long.parseLong(gradeTextField.getText());
                if(grade > 0 && grade < 12)
                    currentParam.add(String.valueOf(grade));
                else
                    JOptionPane.showMessageDialog(schoolkidPanel, "Введите правильный номер класса");

            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(schoolkidPanel, "Введите корректный номер класса");
            }
        });

        scContinueutton.addActionListener(e -> {
            if(currentParam.size() == 2)
                controller.setParam( currentParam, "schoolkid");
            else
                JOptionPane.showMessageDialog(pensionerPanel, "Вы ввели не все параметры");
            currentParam = new ArrayList<>();
            this.dispose();
        });

        scBackButton.addActionListener(e -> {
            this.dispose();
            currentParam = new ArrayList<>();
        });

        /**ученый*/

        sciAddresTextField.addActionListener(e -> {
                String address = sciAddresTextField.getText();
                if(Checker.getInstance().checkString(address))
                    currentParam.add(address);
                else
                    JOptionPane.showMessageDialog(scientistPanel, "Введите корректный адрес работы");
        });


        sciUniversityTextField.addActionListener(e -> {
            try {
                long university = Long.parseLong(sciUniversityTextField.getText());
                if(university > 0)
                    currentParam.add(String.valueOf(university));
                else
                    JOptionPane.showMessageDialog(scientistPanel, "Введите положительный номер университета");

            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(scientistPanel, "Введите корректный номер университета");
            }
        });

        sciContinueButton.addActionListener(e -> {
            if(currentParam.size() == 2)
                controller.setParam( currentParam, "scientist");
            else
                JOptionPane.showMessageDialog(pensionerPanel, "Вы ввели не все параметры");
            currentParam = new ArrayList<>();
            this.dispose();
        });

        sciBackButton.addActionListener(e -> {
            this.dispose();
            currentParam = new ArrayList<>();
        });

        /**студент*/
        sUniversityTextField.addActionListener(e -> {
            try {
                long university = Long.parseLong(sUniversityTextField.getText());
                if(university > 0)
                    currentParam.add(String.valueOf(university));
                else
                    JOptionPane.showMessageDialog(studentPanel, "Введите положительный номер университета");

            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(studentPanel, "Введите корректный номер университета");
            }
        });
        sFacultyTextField.addActionListener(e -> {
            String faculty = sFacultyTextField.getText();
            if(Checker.getInstance().checkString(faculty))
                currentParam.add(faculty);
            else
                JOptionPane.showMessageDialog(studentPanel, "Введите корректный факультет");
        });
        sGroupTextField.addActionListener(e -> {
            try {
                long university = Long.parseLong(sGroupTextField.getText());
                if(university > 0)
                    currentParam.add(String.valueOf(university));
                else
                    JOptionPane.showMessageDialog(studentPanel, "Введите положительный номер группы");

            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(studentPanel, "Введите корректный номер группы");
            }
        });

        sContinueButton.addActionListener(e -> {
            if(currentParam.size() == 3)
                controller.setParam( currentParam, "student");
            else
                JOptionPane.showMessageDialog(pensionerPanel, "Вы ввели не все параметры");
            currentParam = new ArrayList<>();
            this.dispose();
        });

        sBackButton.addActionListener(e -> {
            this.dispose();
            currentParam = new ArrayList<>();
        });

        /**учитель*/

        tUniversityTextField.addActionListener(e -> {
            try {
                long university = Long.parseLong(tUniversityTextField.getText());
                if(university > 0)
                    currentParam.add(String.valueOf(university));
                else
                    JOptionPane.showMessageDialog(teacherPanel, "Введите положительный номер университета");

            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(teacherPanel, "Введите корректный номер университета");
            }
        });

        tFacultyTextField.addActionListener(e -> {
            String faculty = tFacultyTextField.getText();
            if(Checker.getInstance().checkString(faculty))
                currentParam.add(faculty);
            else
                JOptionPane.showMessageDialog(studentPanel, "Введите корректный факультет");
        });

        tContinueButton.addActionListener(e -> {
            if(currentParam.size() == 2)
                controller.setParam( currentParam, "teacher");
            else
                JOptionPane.showMessageDialog(pensionerPanel, "Вы ввели не все параметры");
            currentParam = new ArrayList<>();
            this.dispose();
        });

        tBackButton.addActionListener(e -> {
            this.dispose();
            currentParam = new ArrayList<>();
        });
        /**рабочий*/
        wAddresTextField.addActionListener(e -> {
            String address = wAddresTextField.getText();
            if(Checker.getInstance().checkString(address))
                currentParam.add(address);
            else
                JOptionPane.showMessageDialog(workerPanel, "Введите корректный адрес работы");
        });


        wFirmTextField.addActionListener(e -> {
            String firm = wFirmTextField.getText();
            if(Checker.getInstance().checkString(firm))
                currentParam.add(firm);
            else
                JOptionPane.showMessageDialog(workerPanel, "Введите корректное название фирмы");
        });

        wContinueButton.addActionListener(e -> {
            if(currentParam.size() == 2)
                controller.setParam( currentParam, "worker");
            else
                JOptionPane.showMessageDialog(pensionerPanel, "Вы ввели не все параметры");
            currentParam = new ArrayList<>();
            this.dispose();
        });

        wBackButton.addActionListener(e -> {
            this.dispose();
            currentParam = new ArrayList<>();
        });


    }

}
