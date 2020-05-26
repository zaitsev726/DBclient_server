package Application.UserInterface.Frames.IssuedBook;


import Application.Controllers.IssuedPageController;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
/*
    Таблица поиска истории записей
 */
public class IssuedBookTable extends JFrame {
    private final JTable resultTable;
    private final DefaultTableModel tableModel;
    private ArrayList<String[]> currentInformation;

    private final IssuedPageController controller;

    private final JButton informationButton;
    private final JButton registeredButton;
    private final JButton notRegisteredButton;
    private final JButton backButton;

    public IssuedBookTable(IssuedPageController controller) {
        currentInformation = new ArrayList<>();
        this.controller = controller;
        informationButton = new JButton("Подробнее");
        registeredButton = new JButton("Зарегистрированы");
        notRegisteredButton = new JButton("Не зарегистрированы");
        backButton = new JButton("Очистить и выйти");

        setTitle("Результаты поиска истории");
        setSize(800, 300);

        tableModel = new DefaultTableModel();
        // Заголовки столбцов
        Object[] columnsHeader = new String[]{"ID записи", "ID читателя", "ID издания", "Дата взятия", "Дата возвращения", "Возвращена?", "ID библиотекаря"};
        tableModel.setColumnIdentifiers(columnsHeader);


        resultTable = new JTable(tableModel);

        Box contents = new Box(BoxLayout.Y_AXIS);
        contents.add(new JScrollPane(resultTable));
        getContentPane().add(contents);


        JPanel buttons = new JPanel();
        buttons.add(informationButton);
        buttons.add(registeredButton);
        buttons.add(notRegisteredButton);
        buttons.add(backButton);
        getContentPane().add(buttons, "South");

        initializationListeners();
    }

    private void initializationListeners() {

        resultTable.getModel().addTableModelListener(e -> {
            System.out.println(resultTable.getSelectedRow());
            int row = resultTable.getSelectedRow();
            int column = resultTable.getSelectedColumn();
            if (column == 0) {
                JOptionPane.showMessageDialog(resultTable, "Столбец с ID нельзя менять");
            } else if(column == 5){
                JOptionPane.showMessageDialog(resultTable, "Столбец с возвратом нельзя менять");
            }
            else if (row >= 0) {
                try {
                    controller.queryForUpdateIssuedBook(Long.parseLong(String.valueOf(tableModel.getValueAt(row, 0))), Long.parseLong(String.valueOf(tableModel.getValueAt(row, 1))),
                            Long.parseLong(String.valueOf(tableModel.getValueAt(row, 2))),
                            new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(tableModel.getValueAt(row,3))),
                            new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(tableModel.getValueAt(row,4))),
                            Boolean.parseBoolean(String.valueOf(tableModel.getValueAt(row, 5))),
                            Long.parseLong(String.valueOf(tableModel.getValueAt(row, 6))), column);
                } catch (ArrayIndexOutOfBoundsException ignored) {
                } catch (ParseException parseException) {
                    JOptionPane.showMessageDialog(resultTable, "Неправильный формат даты.Ожидается yyyy-MM-dd");
                }
            }
        });

        informationButton.addActionListener(e ->{

            int row = resultTable.getSelectedRow();
            if (row >= 0)
                controller.showCurrentInformation(Long.parseLong(String.valueOf(tableModel.getValueAt(row, 2))));
            else
                JOptionPane.showMessageDialog(resultTable, "Выберите строчку");
        });

        registeredButton.addActionListener(e ->{
            tableModel.setRowCount(0);
            ArrayList<String[]> a = currentInformation;
            currentInformation = new ArrayList<>();
            controller.queryForSelectRegistered(a);
        });

        notRegisteredButton.addActionListener(e ->{
            tableModel.setRowCount(0);
            ArrayList<String[]> a = currentInformation;
            currentInformation = new ArrayList<>();
            controller.queryForSelectNotRegistered(a);
        });



        backButton.addActionListener(e -> {
            tableModel.setRowCount(0);
            currentInformation = new ArrayList<>();
            this.dispose();
        });
    }

    public void updateTable(ArrayList<String[]> array) {
        for (String[] row : array) {
            boolean adding = true;
            for (String[] cur : currentInformation) {
                if (cur[0].equals(row[0])) {
                    adding = false;
                    break;
                }
            }
            if (adding) {
                tableModel.addRow(row);
                currentInformation.add(row);
            }
        }
    }
}
