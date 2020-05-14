package Appli.UserInterface.Frames.Edition.InvertaryInfo;

import Appli.Controllers.EditionsPageController;
import Appli.Entities.Information;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class SearchEditionForm extends JFrame{
    private JTable resultTable;
    private DefaultTableModel tableModel;
    private ArrayList<String[]> currentLibraries;

    private EditionsPageController controller;
    private JButton removeRowButton;
    private JButton backButton;

    // Заголовки столбцов
    private final Object[] columnsHeader = new String[]{"ID издания", "ID библиотеки", "Зал", "Стеллаж", "Полка"};

    public SearchEditionForm(EditionsPageController controller) {
        currentLibraries = new ArrayList<>();
        this.controller = controller;
        removeRowButton = new JButton("Удалить выбранную строку");
        backButton = new JButton("Очистить и выйти");

        setTitle("Результаты поиска изданий");
        setSize(600, 300);

        tableModel = new DefaultTableModel();
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
                }else if(column == 4){
                    JOptionPane.showMessageDialog(resultTable, "Столбец с популярностью нельзя менять");
                }
                else if (row >= 0) {
                    try {
                        controller.queryForUpdateInformation(Long.parseLong(String.valueOf(tableModel.getValueAt(row, 0))), Long.parseLong(String.valueOf(tableModel.getValueAt(row, 1))),
                                String.valueOf(tableModel.getValueAt(row, 2)), String.valueOf(tableModel.getValueAt(row, 3)), Integer.parseInt(String.valueOf(tableModel.getValueAt(row, 4))));
                    } catch (ArrayIndexOutOfBoundsException ignored) {
                    }
                }
            }
        });


        removeRowButton.addActionListener(e -> {
            // Номер выделенной строки
            int row = resultTable.getSelectedRow();
            // Удаление выделенной строки
            controller.queryForDeleteInformation(Long.parseLong(String.valueOf(tableModel.getValueAt(row, 0))));
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
