package Appli.UserInterface.Frames.Edition.InvertaryInfo;

import Appli.Controllers.EditionsPageController;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class SearchEditionForm extends JFrame {
    private JTable resultTable;
    private DefaultTableModel tableModel;
    private ArrayList<String[]> currentEditions;

    private EditionsPageController controller;

    private JButton removeRowButton;
    private JButton backButton;
    private JButton libraryButton;
    private JButton rulesButton;

    // Заголовки столбцов
    private final Object[] columnsHeader = new String[]{"ID издания", "ID библиотеки", "Зал", "Стеллаж", "Полка", "Дата добавления", "Дата удаления"};

    public SearchEditionForm(EditionsPageController controller) {
        currentEditions = new ArrayList<>();
        this.controller = controller;
        removeRowButton = new JButton("Удалить выбранную строку");
        libraryButton = new JButton("Информация о библиотеке");
        rulesButton = new JButton("Правила издания");
        backButton = new JButton("Очистить и выйти");


        setTitle("Результаты поиска изданий");
        setSize(800, 300);

        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(columnsHeader);


        resultTable = new JTable(tableModel);

        Box contents = new Box(BoxLayout.Y_AXIS);
        contents.add(new JScrollPane(resultTable));
        getContentPane().add(contents);


        JPanel buttons = new JPanel();
        buttons.add(removeRowButton);
        buttons.add(libraryButton);
        buttons.add(rulesButton);
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
                        controller.queryForUpdateEdition(Long.parseLong(String.valueOf(tableModel.getValueAt(row, 0))), Long.parseLong(String.valueOf(tableModel.getValueAt(row, 1))),
                                Integer.parseInt(String.valueOf(tableModel.getValueAt(row, 2))), Integer.parseInt(String.valueOf(tableModel.getValueAt(row, 3))), Integer.parseInt(String.valueOf(tableModel.getValueAt(row, 4))),
                                        new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(tableModel.getValueAt(row,5))),
                                        new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(tableModel.getValueAt(row,6))));
                    } catch (ArrayIndexOutOfBoundsException ignored) {
                    } catch (ParseException parseException) {
                        JOptionPane.showMessageDialog(resultTable, "Неправильный формат даты.Ожидается yyyy-MM-dd");
                    }
                }
            }
        });


        removeRowButton.addActionListener(e -> {
            // Номер выделенной строки
            int row = resultTable.getSelectedRow();
            if(row >= 0) {
                // Удаление выделенной строки
                controller.queryForDeleteEdition(Long.parseLong(String.valueOf(tableModel.getValueAt(row, 0))));
                tableModel.removeRow(row);
            }else
                JOptionPane.showMessageDialog(resultTable, "Выберите строку");
        });

        libraryButton.addActionListener(e -> {
            // Номер выделенной строки
            int row = resultTable.getSelectedRow();
            // Удаление выделенной строки
            if(row >= 0) {
                controller.showCurrentLibrary(Long.parseLong(String.valueOf(tableModel.getValueAt(row, 1))));
            }else
                JOptionPane.showMessageDialog(resultTable, "Выберите строку");
        });

        rulesButton.addActionListener(e -> {
            // Номер выделенной строки
            int row = resultTable.getSelectedRow();
            // Удаление выделенной строки
            if(row >= 0) {
                controller.showCurrentRules(Long.parseLong(String.valueOf(tableModel.getValueAt(row, 0))));
            }else
                JOptionPane.showMessageDialog(resultTable, "Выберите строку");
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
