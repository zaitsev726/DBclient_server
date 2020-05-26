package Application.UserInterface.Frames.Library;

import Application.Controllers.LibraryPageController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

/*
    Таблица поиска библиотек
 */
public class LibraryTable extends JFrame {
    private final JTable resultTable;
    private final DefaultTableModel tableModel;
    private ArrayList<String[]> currentLibraries;

    private final LibraryPageController controller;
    private final JButton removeRowButton;
    private final JButton backButton;

    public LibraryTable(LibraryPageController controller) {
        currentLibraries = new ArrayList<>();
        this.controller = controller;
        removeRowButton = new JButton("Удалить выбранную строку");
        backButton = new JButton("Очистить и выйти");

        setTitle("Результаты поиска");
        setSize(400, 300);

        tableModel = new DefaultTableModel();
        // Заголовки столбцов
        Object[] columnsHeader = new String[]{"ID библиотеки", "Кол-во залов"};
        tableModel.setColumnIdentifiers(columnsHeader);


        resultTable = new JTable(tableModel);

        Box contents = new Box(BoxLayout.Y_AXIS);
        contents.add(new JScrollPane(resultTable));
        getContentPane().add(contents);


        JPanel buttons = new JPanel();
        buttons.add(removeRowButton);
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
            } else if (row >= 0) {
                try {
                    controller.queryForUpdateLibrary(Long.parseLong(String.valueOf(tableModel.getValueAt(row, 0))), Long.parseLong(String.valueOf(tableModel.getValueAt(row, 1))));
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
            }
        });


        removeRowButton.addActionListener(e -> {
            // Номер выделенной строки
            int row = resultTable.getSelectedRow();
            // Удаление выделенной строки
            controller.queryForDeleteLibrary(Long.parseLong(String.valueOf(tableModel.getValueAt(row, 0))));
            tableModel.removeRow(row);
        });

        backButton.addActionListener(e -> {
            tableModel.setRowCount(0);
            currentLibraries = new ArrayList<>();
            this.dispose();
        });
    }

    public void updateTable(ArrayList<String[]> array) {
        for (String[] row : array) {
            boolean adding = true;
            for (String[] cur : currentLibraries) {
                if (cur[0].equals(row[0])) {
                    adding = false;
                    break;
                }
            }
            if (adding) {
                tableModel.addRow(row);
                currentLibraries.add(row);
            }
        }
    }
}
