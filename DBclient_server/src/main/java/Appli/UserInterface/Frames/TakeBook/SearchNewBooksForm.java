package Appli.UserInterface.Frames.TakeBook;

import Appli.Controllers.TakeBookPageController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class SearchNewBooksForm extends JFrame {
    private JTable resultTable;
    private DefaultTableModel tableModel;
    private ArrayList<String[]> currentNewEditions;

    private TakeBookPageController controller;

    private JButton informationButton;
    private JButton takeButton;
    private JButton backButton;

    // Заголовки столбцов
    private final Object[] columnsHeader = new String[]{"ID записи", "ID издания", "ID библиотекаря"};

    public SearchNewBooksForm(TakeBookPageController controller) {
        currentNewEditions = new ArrayList<>();
        this.controller = controller;
        informationButton = new JButton("Подробнее");
        takeButton = new JButton("Взять книгу");
        backButton = new JButton("Очистить и выйти");

        setTitle("Результаты поиска истории");
        setSize(500, 300);

        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(columnsHeader);


        resultTable = new JTable(tableModel);

        Box contents = new Box(BoxLayout.Y_AXIS);
        contents.add(new JScrollPane(resultTable));
        getContentPane().add(contents);


        JPanel buttons = new JPanel();
        buttons.add(informationButton);
        buttons.add(takeButton);
        buttons.add(backButton);
        getContentPane().add(buttons, "South");

        initializationListeners();
        //setVisible(true);
    }

    private void initializationListeners() {


        informationButton.addActionListener(e -> {

            int row = resultTable.getSelectedRow();
            if (row >= 0)
                controller.showCurrentInformation(Long.parseLong(String.valueOf(tableModel.getValueAt(row, 1))));
            else
                JOptionPane.showMessageDialog(resultTable, "Выберите строчку");
        });

        takeButton.addActionListener(e -> {

            int row = resultTable.getSelectedRow();
            if (row >= 0) {
                long id_edition = Long.parseLong(String.valueOf(tableModel.getValueAt(row, 1)));
                long id_librarian = Long.parseLong(String.valueOf(tableModel.getValueAt(row, 2)));
                tableModel.setRowCount(0);
                currentNewEditions = new ArrayList<>();
                controller.queryForTakeBook(id_edition, id_librarian);
            } else
                JOptionPane.showMessageDialog(resultTable, "Выберите строчку");

        });

        backButton.addActionListener(e -> {
            tableModel.setRowCount(0);
            currentNewEditions = new ArrayList<>();
            this.dispose();
        });
    }

    public void updateTable(ArrayList<String[]> array) {
        for (String[] row : array) {
            boolean adding = true;
            for (String[] cur : currentNewEditions) {
                if (cur[0].equals(row[0])) {
                    adding = false;
                    break;
                }
            }
            if (adding) {
                tableModel.addRow(row);
                currentNewEditions.add(row);
            }
        }
    }
}
