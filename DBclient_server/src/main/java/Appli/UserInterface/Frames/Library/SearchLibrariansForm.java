package Appli.UserInterface.Frames.Library;

import Appli.Controllers.LibraryPageController;
import Appli.Entities.Librarian;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class SearchLibrariansForm extends JFrame {
    private JTable resultTable;
    private DefaultTableModel tableModel;
    private ArrayList<String[]> currentLibrarians;

    private LibraryPageController controller;
    private JButton removeRowButton;
    private JButton backButton;
    private JButton addRowButton;

    // Заголовки столбцов
    private final Object[] columnsHeader = new String[]{"ID работника", "ID библиотеки", "Норер зала"};

    public SearchLibrariansForm(LibraryPageController controller) {
        currentLibrarians = new ArrayList<>();
        this.controller = controller;
        removeRowButton = new JButton("Удалить выбранную строку");
        addRowButton = new JButton("Добавить работника");
        backButton = new JButton("Очистить и выйти");

        setTitle("Результаты поиска");
        setSize(400, 300);

        tableModel = new DefaultTableModel();
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
        //setVisible(true);
    }

    private void initializationListeners() {

        resultTable.getModel().addTableModelListener(new TableModelListener() {

            public void tableChanged(TableModelEvent e) {
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
