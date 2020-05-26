package Application.UserInterface.Frames.Edition.Characteristic;

import Application.Controllers.EditionsPageController;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

/*
    Таблица поиска Характеристик издания
 */
public class CharacteristicTable extends JFrame {
    private final JTable resultTable;
    private final DefaultTableModel tableModel;
    private ArrayList<String[]> currentCharacteristic;

    private final EditionsPageController controller;
    private final JButton removeRowButton;
    private final JButton backButton;

    public CharacteristicTable(EditionsPageController controller) {
        currentCharacteristic = new ArrayList<>();
        this.controller = controller;
        removeRowButton = new JButton("Удалить выбранную строку");
        backButton = new JButton("Очистить и выйти");

        setTitle("Результаты поиска изданий");
        setSize(600, 300);

        tableModel = new DefaultTableModel();
        // Заголовки столбцов
        Object[] columnsHeader = new String[]{"ID издания", "Тип издния", "Автор", "Название"};
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
                    controller.queryForUpdateCharacteristic(Long.parseLong(String.valueOf(tableModel.getValueAt(row, 0))),String.valueOf(tableModel.getValueAt(row, 1)),
                            String.valueOf(tableModel.getValueAt(row, 2)), String.valueOf(tableModel.getValueAt(row, 3)));
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
            }
        });


        removeRowButton.addActionListener(e -> {
            // Номер выделенной строки
            int row = resultTable.getSelectedRow();
            // Удаление выделенной строки
            controller.queryForDeleteCharacteristic(Long.parseLong(String.valueOf(tableModel.getValueAt(row, 0))));
            tableModel.removeRow(row);
        });

        backButton.addActionListener(e -> {
            tableModel.setRowCount(0);
            currentCharacteristic = new ArrayList<>();
            this.dispose();
        });
    }

    public void updateTable(ArrayList<String[]> array) {
        for (String[] row : array) {
            boolean adding = true;
            for (String[] cur : currentCharacteristic) {
                if (cur[0].equals(row[0])) {
                    adding = false;
                    break;
                }
            }
            if (adding) {
                tableModel.addRow(row);
                currentCharacteristic.add(row);
            }
        }
    }
}