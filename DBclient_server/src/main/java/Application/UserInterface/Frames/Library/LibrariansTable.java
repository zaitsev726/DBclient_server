package Application.UserInterface.Frames.Library;

import Application.Controllers.LibraryPageController;
import Application.Entities.Librarian;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

/*
    Таблица поиска работников библиотеки
 */
public class LibrariansTable extends JFrame {
    private final JTable resultTable;
    private final DefaultTableModel tableModel;
    private ArrayList<String[]> currentLibrarians;

    private final LibraryPageController controller;
    private final JButton removeRowButton;
    private final JButton backButton;
    private final JButton addRowButton;

    public LibrariansTable(LibraryPageController controller) {
        currentLibrarians = new ArrayList<>();
        this.controller = controller;
        removeRowButton = new JButton("Удалить выбранную строку");
        addRowButton = new JButton("Добавить работника");
        backButton = new JButton("Очистить и выйти");

        setTitle("Результаты поиска");
        setSize(400, 300);

        tableModel = new DefaultTableModel();
        // Заголовки столбцов
        Object[] columnsHeader = new String[]{"ID работника", "ID библиотеки", "Номер зала"};
        tableModel.setColumnIdentifiers(columnsHeader);


        resultTable = new JTable(tableModel);

        Box contents = new Box(BoxLayout.Y_AXIS);
        contents.add(new JScrollPane(resultTable));
        getContentPane().add(contents);


        JPanel buttons = new JPanel();
        buttons.add(removeRowButton);
        buttons.add(addRowButton);
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
                    controller.queryForUpdateLibrarian(Long.parseLong(String.valueOf(tableModel.getValueAt(row, 0))), Long.parseLong(String.valueOf(tableModel.getValueAt(row, 1))),Long.parseLong(String.valueOf(tableModel.getValueAt(row, 2))));
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
            }
        });

        removeRowButton.addActionListener(e -> {
            // Номер выделенной строки
            int row = resultTable.getSelectedRow();
            // Удаление выделенной строки
            controller.queryForDeleteLibrarian(Long.parseLong(String.valueOf(tableModel.getValueAt(row, 0))));
            tableModel.removeRow(row);
        });

        addRowButton.addActionListener(e -> {
            // Номер выделенной строки
            int idx = resultTable.getSelectedRow();
            // Вставка новой строки после выделенной
            if(idx < 0)
                idx = 0;

            int library = Integer.parseInt(String.valueOf(resultTable.getValueAt(idx, 1)));
            Librarian librarian = controller.queryForInsertLibrarian(library,1);
            tableModel.insertRow(idx + 1, new String[] {
                    String.valueOf(librarian.getId_librarian()),
                    String.valueOf(library), "1"});
        });

        backButton.addActionListener(e -> {
            tableModel.setRowCount(0);
            currentLibrarians = new ArrayList<>();
            this.dispose();
        });
    }

    public void updateTable(ArrayList<String[]> array) {
        for (String[] row : array) {
            boolean adding = true;
            for (String[] cur : currentLibrarians) {
                if (cur[0].equals(row[0])) {
                    adding = false;
                    break;
                }
            }
            if (adding) {
                tableModel.addRow(row);
                currentLibrarians.add(row);
            }
        }
    }
}
