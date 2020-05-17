package Appli.UserInterface.Frames.Edition.Rules;

import Appli.Controllers.EditionsPageController;
import Appli.Entities.Rule;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class SearchRulesForm extends JFrame {
    private final JTable resultTable;
    private final DefaultTableModel tableModel;
    private ArrayList<String[]> currentRules;

    private final EditionsPageController controller;
    private final JButton removeRowButton;
    private final JButton addRowButton;
    private final JButton backButton;

    public SearchRulesForm(EditionsPageController controller) {
        currentRules = new ArrayList<>();
        this.controller = controller;
        removeRowButton = new JButton("Удалить выбранную строку");
        addRowButton = new JButton("Добавить правило");
        backButton = new JButton("Очистить и выйти");

        setTitle("Правила издания");
        setSize(800, 300);

        tableModel = new DefaultTableModel();
        // Заголовки столбцов
        Object[] columnsHeader = new String[]{"ID правила", "ID издания", "Правило"};
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

        resultTable.getModel().addTableModelListener(new TableModelListener() {

            public void tableChanged(TableModelEvent e) {
                System.out.println(resultTable.getSelectedRow());
                int row = resultTable.getSelectedRow();
                int column = resultTable.getSelectedColumn();
                if (column == 0) {
                    JOptionPane.showMessageDialog(resultTable, "Столбец с ID нельзя менять");
                } else if (row >= 0) {
                    try {
                        controller.queryForUpdateRule(Long.parseLong(String.valueOf(tableModel.getValueAt(row, 0))), Long.parseLong(String.valueOf(tableModel.getValueAt(row, 1))),
                                String.valueOf(tableModel.getValueAt(row,2)));

                    } catch (ArrayIndexOutOfBoundsException ignored) {}

                }
            }
        });


        removeRowButton.addActionListener(e -> {
            // Номер выделенной строки
            int row = resultTable.getSelectedRow();
            // Удаление выделенной строки
            controller.queryForDeleteRule(Long.parseLong(String.valueOf(tableModel.getValueAt(row, 0))));
            tableModel.removeRow(row);
        });

        addRowButton.addActionListener(e -> {
            // Номер выделенной строки
            int idx = resultTable.getSelectedRow();
            // Вставка новой строки после выделенной
            if(idx < 0)
                idx = 0;

            int id_edition = Integer.parseInt(String.valueOf(resultTable.getValueAt(idx, 1)));
            Rule rule = controller.queryForInsertRule(id_edition, "Неизвестно");
            tableModel.insertRow(idx + 1, new String[]{
                     String.valueOf(rule.getId_rule()),
                     String.valueOf(id_edition), "Неизвестно"});
        });


        backButton.addActionListener(e -> {
            tableModel.setRowCount(0);
            currentRules = new ArrayList<>();
            this.dispose();
        });
    }

    public void updateTable(ArrayList<String[]> array) {
        for (String[] row : array) {
            boolean adding = true;
            for (String[] cur : currentRules) {
                if (cur[0].equals(row[0])) {
                    adding = false;
                    break;
                }
            }
            if (adding) {
                tableModel.addRow(row);
                currentRules.add(row);
            }
        }
    }
}