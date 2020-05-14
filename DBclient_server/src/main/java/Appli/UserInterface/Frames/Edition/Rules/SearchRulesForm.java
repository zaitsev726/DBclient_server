package Appli.UserInterface.Frames.Edition.Rules;

import Appli.Controllers.EditionsPageController;
import Appli.Entities.Information;
import Appli.Entities.Rule;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class SearchRulesForm extends JFrame {
    private JTable resultTable;
    private DefaultTableModel tableModel;
    private ArrayList<String[]> currentLibraries;

    private EditionsPageController controller;
    private JButton removeRowButton;
    private JButton addRowButton;
    private JButton backButton;

    // Заголовки столбцов
    private final Object[] columnsHeader = new String[]{"ID правила", "ID издания", "Правило"};

    public SearchRulesForm(EditionsPageController controller) {
        currentLibraries = new ArrayList<>();
        this.controller = controller;
        removeRowButton = new JButton("Удалить выбранную строку");
        addRowButton = new JButton("Добавить правило");
        backButton = new JButton("Очистить и выйти");

        setTitle("Правила издания");
        setSize(800, 300);

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
            Rule information = controller.queryForInsertRule(id_edition, "Неизвестно");
            tableModel.insertRow(idx + 1, new String[]{
                    // String.valueOf(information.getId_record()),
                    //   String.valueOf(id_edition), "Неизвестно", "Неизвестно", "0"});
            });
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