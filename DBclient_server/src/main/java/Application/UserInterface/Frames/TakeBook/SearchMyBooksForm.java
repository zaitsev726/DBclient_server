package Application.UserInterface.Frames.TakeBook;

import Application.Controllers.TakeBookPageController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class SearchMyBooksForm extends JFrame {
    private JTable resultTable;
    private DefaultTableModel tableModel;
    private ArrayList<String[]> currentEditions;

    private TakeBookPageController controller;

    private JButton informationButton;
    private JButton returnButton;
    private JButton backButton;

    // Заголовки столбцов
    private final Object[] columnsHeader = new String[]{"ID записи", "ID издания", "Дата взятия", "Дата возвращения", "Возвращена?", "ID библиотекаря"};

    public SearchMyBooksForm(TakeBookPageController controller) {
        currentEditions = new ArrayList<>();
        this.controller = controller;
        informationButton = new JButton("Подробнее");
        returnButton = new JButton("Вернуть");
        backButton = new JButton("Очистить и выйти");

        setTitle("Результаты поиска истории");
        setSize(800, 300);

        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(columnsHeader);


        resultTable = new JTable(tableModel);

        Box contents = new Box(BoxLayout.Y_AXIS);
        contents.add(new JScrollPane(resultTable));
        getContentPane().add(contents);


        JPanel buttons = new JPanel();
        buttons.add(informationButton);
        buttons.add(returnButton);
        buttons.add(backButton);
        getContentPane().add(buttons, "South");

        initializationListeners();
        //setVisible(true);
    }

    private void initializationListeners() {


        informationButton.addActionListener(e ->{

            int row = resultTable.getSelectedRow();
            if (row >= 0)
                controller.showCurrentInformation(Long.parseLong(String.valueOf(tableModel.getValueAt(row, 1))));
            else
                JOptionPane.showMessageDialog(resultTable, "Выберите строчку");
        });

        returnButton.addActionListener(e ->{

            int row = resultTable.getSelectedRow();
            if (row >= 0) {
                if(!Boolean.valueOf((String) tableModel.getValueAt(row,4))) {
                    long id_edition = Long.parseLong(String.valueOf(tableModel.getValueAt(row, 1)));
                    tableModel.setRowCount(0);
                    currentEditions = new ArrayList<>();
                    controller.queryForReturn(id_edition);
                }else{
                    JOptionPane.showMessageDialog(resultTable, "Книга уже возвращена!");
                }
            }
            else
                JOptionPane.showMessageDialog(resultTable, "Выберите строчку");

        });

        backButton.addActionListener(e -> {
            tableModel.setRowCount(0);
            currentEditions = new ArrayList<>();
            this.dispose();
        });
    }

    public void updateTable(ArrayList<String[]> array) {
        for (String[] row : array) {
            boolean adding = true;
            for (String[] cur : currentEditions) {
                if (cur[0].equals(row[0])) {
                    adding = false;
                    break;
                }
            }
            if (adding) {
                tableModel.addRow(row);
                currentEditions.add(row);
            }
        }
    }
}
